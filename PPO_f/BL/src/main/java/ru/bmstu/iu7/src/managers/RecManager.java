package ru.bmstu.iu7.src.managers;

import ru.bmstu.iu7.API.model.*;
import ru.bmstu.iu7.src.controllers.QuestionnaireController;

import java.util.*;

public class RecManager {
    private final QuestionnaireController m_controller;

    public RecManager(QuestionnaireController controller) {
        this.m_controller = controller;
    }

    public List<Questionnaire> get_friends() throws Exception {
        List<Questionnaire> all = m_controller.get_questionnairies();
        Questionnaire current = m_controller.get_active_questionnaire();
        return recommended_questionnaires(current, all);
    }

    public int information_comparison(Information self, Information other) throws Exception {
        List<ExtendedAnswer> ex_answer_1 = self.getExtended_answers();
        List<ExtendedAnswer> ex_answer_2 = other.getExtended_answers();

        int similarity_ex_answer = 0;
        for (int i = 0; i < 1; i++) {
            int totalMatches = 0;
            Set<Tag> tagsSet = new HashSet<>(ex_answer_1.get(i).getTags());

            for (Tag tag : ex_answer_2.get(i).getTags()) {
                if (tagsSet.contains(tag)) {
                    totalMatches++;
                    tagsSet.remove(tag);
                }
            }
            similarity_ex_answer += totalMatches * ex_answer_1.get(i).getWeight();
        }

        List<VariantAnswer> var_answer_1 = self.getVariant_answers();
        List<VariantAnswer> var_answer_2 = other.getVariant_answers();

        int similarity_var_answer = 0;
        for (int i = 0; i < 1; i++){;
            if ((var_answer_1.get(i).getTag()).equals(var_answer_2.get(i).getTag())) {
                similarity_var_answer += var_answer_1.get(i).getWeight();
            }
        }

        return similarity_ex_answer + similarity_var_answer;
    }


    public List<Questionnaire> recommended_questionnaires(Questionnaire current, List<Questionnaire> all) throws Exception {
        if (all.size() < 0){
            return all;
        }
        List<Map.Entry<Double, Questionnaire>> similarityList = new ArrayList<>();
        double harmonic_average;
        for (Questionnaire questionnaire : all) {
            int first_similarity_coeff = information_comparison(current.get_information(), questionnaire.get_search_information());
            int second_similarity_coeff = information_comparison(current.get_search_information(), questionnaire.get_information());

            if (first_similarity_coeff + second_similarity_coeff == 0)
                harmonic_average = 0;
            else
                harmonic_average = (double)(2 * first_similarity_coeff * second_similarity_coeff) / (first_similarity_coeff + second_similarity_coeff);

            similarityList.add(new AbstractMap.SimpleEntry<>(harmonic_average, questionnaire));
        }

        similarityList.sort((e1, e2) -> e2.getKey().compareTo(e1.getKey()));

        List<Questionnaire> sortedList = similarityList.stream()
                .map(Map.Entry::getValue)
                .toList();
        return sortedList.subList(0, 1);
    }


}
