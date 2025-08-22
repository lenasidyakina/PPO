package ru.bmstu.iu7.src.managers;

import ru.bmstu.iu7.API.model.*;
import ru.bmstu.iu7.src.controllers.QuestionnaireController;

import java.util.*;


public class RecManager {
    private final QuestionnaireController m_controller;
    private boolean is_update_running = false;
    public class ScheduledTask extends TimerTask {

        public void run(){
            List<IQuestionnaire> all;
            int lower = 0;
            int upper = 1000;
            IQuestionnaire current = m_controller.get_active_questionnaire();
            m_controller.sort_all_questionnaire();
            do{
                try {
                    all = m_controller.get_questionnairies(lower, upper);
                    recommended_questionnaires(current, all);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                lower += 1000;
                upper += 1000;
            } while (!all.isEmpty());
            try {
                m_controller.update_req_cache();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public RecManager(QuestionnaireController controller) {
        this.m_controller = controller;
    }

    public List<IQuestionnaire> get_friends() throws Exception {
        if (m_controller.get_active_questionnaire() == null) {
            throw new Exception();
        }
        else
        {
            if (!is_update_running)
            {
                Timer time = new Timer();
                ScheduledTask st = new ScheduledTask();
                time.schedule(st, 0, 1000 * 60 * 30);
                st.run();
            }
        }
        return m_controller.get_quest_in_cache();
    }

    @SuppressWarnings("unchecked")
    public int information_comparison(IInformation self, IInformation other) throws Exception {
        List<IExtendedAnswer> ex_answer_1 = (List<IExtendedAnswer>)self.getExtendedAnswers();
        List<IExtendedAnswer> ex_answer_2 = (List<IExtendedAnswer>)other.getExtendedAnswers();

        int similarity_ex_answer = 0;
        for (int i = 0; i < 1; i++) {
            int totalMatches = 0;
            Set<ITag> tagsSet = new HashSet<>(ex_answer_1.get(i).getTags());

            for (ITag tag : ex_answer_2.get(i).getTags()) {
                if (tagsSet.contains(tag)) {
                    totalMatches++;
                    tagsSet.remove(tag);
                }
            }
            similarity_ex_answer += totalMatches * ex_answer_1.get(i).getWeight();
        }

        List<IVariantAnswer> var_answer_1 = (List<IVariantAnswer>)self.getVariantAnswers();
        List<IVariantAnswer> var_answer_2 = (List<IVariantAnswer>)other.getVariantAnswers();

        int similarity_var_answer = 0;
        for (int i = 0; i < 1; i++){;
            if ((var_answer_1.get(i).getTag()).equals(var_answer_2.get(i).getTag())) {
                similarity_var_answer += var_answer_1.get(i).getWeight();
            }
        }

        return similarity_ex_answer + similarity_var_answer;
    }


    public void recommended_questionnaires(IQuestionnaire current, List<IQuestionnaire> all) throws Exception {
        double harmonic_average;
        double max_harmonic_average;
        double harmonic_average_norm;
        int count_extnd_tags = 3;
        int count_var_tags = 1;
        for (IQuestionnaire questionnaire : all) {
            int first_similarity_coeff = information_comparison(current.getInformation(), questionnaire.getSearchInformation());
            int second_similarity_coeff = information_comparison(current.getSearchInformation(), questionnaire.getInformation());

            if (first_similarity_coeff + second_similarity_coeff == 0)
                harmonic_average_norm = 0;
            else {
                harmonic_average = (double) (2 * first_similarity_coeff * second_similarity_coeff) / (first_similarity_coeff + second_similarity_coeff);
                max_harmonic_average = 10 * (count_extnd_tags + count_var_tags);
                harmonic_average_norm = harmonic_average / max_harmonic_average;
            }
            if ((harmonic_average_norm > 0.25) && (!m_controller.is_in_black(questionnaire) && (!m_controller.is_in_fav(questionnaire))))
                //m_req_cache.add(new AbstractMap.SimpleEntry<>(harmonic_average_norm, questionnaire));
                m_controller.add_in_cache_list(harmonic_average_norm, questionnaire.getId());
        }
    }


}
