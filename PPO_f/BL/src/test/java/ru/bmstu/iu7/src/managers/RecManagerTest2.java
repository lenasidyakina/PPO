package ru.bmstu.iu7.src.managers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bmstu.iu7.API.IML_port;
import ru.bmstu.iu7.API.IQuestionnaireRepository;
import ru.bmstu.iu7.API.IReqCacheRepository;
import ru.bmstu.iu7.API.model.*;
import ru.bmstu.iu7.src.controllers.QuestionnaireController;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecManagerTest2 {

    @Mock
    IML_port iml_port;
    @Mock
    IQuestionnaireRepository questionnaire_repository;
    @Mock
    IReqCacheRepository req_cache_repository;



    @Test
    void information_comparison() throws Exception {
        Information info1 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking")))))));

        Information info2 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))))));

        QuestionnaireController qc = new QuestionnaireController(iml_port, questionnaire_repository, req_cache_repository);
        Questionnaire quest  = new Questionnaire(0, info1, info2);
        qc.set_active_questionnaire(quest);
        qc.set_req_cache(new ReqCache(quest.get_id(), List.of()));
        RecManager rec_manager = new RecManager(qc);

        int coeff = rec_manager.information_comparison(info1, info2);
        assertEquals(coeff, 2);
    }

    @Test
    void information_comparison_2() throws Exception {
        Information info1 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking")))))));

        Information info2 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        QuestionnaireController qc = new QuestionnaireController(iml_port, questionnaire_repository, req_cache_repository);
        Questionnaire quest  = new Questionnaire(0, info1, info2);
        qc.set_active_questionnaire(quest);
        qc.set_req_cache(new ReqCache(quest.get_id(), List.of()));
        RecManager rec_manager = new RecManager(qc);
        int coeff = rec_manager.information_comparison(info1, info2);
        assertEquals(coeff, 0);
    }

    @Test
    void information_comparison_3() throws Exception {
        Information info1 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))), 2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));

        Information info2 = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))), 2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));

        QuestionnaireController qc = new QuestionnaireController(iml_port, questionnaire_repository, req_cache_repository);
        Questionnaire quest  = new Questionnaire(0, info1, info2);
        qc.set_active_questionnaire(quest);
        qc.set_req_cache(new ReqCache(quest.get_id(), List.of()));
        RecManager rec_manager = new RecManager(qc);
        int coeff = rec_manager.information_comparison(info1, info2);
        assertEquals(coeff, 5);
    }

    @Test
    void get_friends() throws Exception {
        Information cur_info = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking")))))));

        Information cur_info_search = new Information(1,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire cur_questionnaire = new Questionnaire(0, cur_info, cur_info_search);

        Information info = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking")))))));

        Information info_search = new Information(3,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire questionnaire = new Questionnaire(1, info, info_search);

        QuestionnaireController qc = new QuestionnaireController(iml_port, questionnaire_repository, req_cache_repository);
        qc.set_active_questionnaire(cur_questionnaire);
        qc.set_req_cache(new ReqCache(cur_questionnaire.get_id(), List.of()));

        RecManager rec_manager = new RecManager(qc);
        rec_manager.get_friends();
    }

    @Test
    void get_friends_2() throws Exception {
        Information cur_info = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking")))))));

        Information cur_info_search = new Information(1,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire cur_questionnaire = new Questionnaire(0, cur_info, cur_info_search);

        Information info = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking")))))));

        Information info_search = new Information(3,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire questionnaire = new Questionnaire(1, info, info_search);

        QuestionnaireController qc = new QuestionnaireController(iml_port, questionnaire_repository, req_cache_repository);
        qc.set_active_questionnaire(cur_questionnaire);
        qc.set_req_cache(new ReqCache(cur_questionnaire.get_id(), List.of()));
        Mockito.lenient().doReturn(List.of(questionnaire)).when(questionnaire_repository).find_interval(0, 1000);
        Mockito.lenient().doReturn(questionnaire).when(questionnaire_repository).findQuestionnaire(1);
        Mockito.lenient().doReturn(List.of()).when(questionnaire_repository).find_interval(1000, 2000);
        List<Map.Entry<Integer, Double>> mockList = List.of(
                new AbstractMap.SimpleEntry<>(1, 1.0)
        );
        Mockito.lenient().doReturn(mockList).when(req_cache_repository).findAll(cur_questionnaire.get_id());

        RecManager rec_manager = new RecManager(qc);

        List<Questionnaire> req_list = rec_manager.get_friends();
        //Mockito.verify(req_cache_repository).insert(0, 100);
        //List<Questionnaire> recommendations = rec_manager.recommended_questionnaires(cur_questionnaire, List.of(questionnaire));
        //rec_manager.recommended_questionnaires(cur_questionnaire, List.of(questionnaire));
        Questionnaire questionnaire_test = new Questionnaire(1, info, info_search);
        assertEquals(req_list.getFirst(), questionnaire_test);
        //assertEquals(((rec_manager.get_req_cache()).getFirst()) , questionnaire_test);
    }

    @Test
    void get_friends_3() throws Exception {
        Information cur_info = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking")))))));

        Information cur_info_search = new Information(1,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire cur_questionnaire = new Questionnaire(0, cur_info, cur_info_search);

        Information info_1 = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));

        Information info_search_1 = new Information(3,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire questionnaire_1 = new Questionnaire(1, info_1, info_search_1);

        Information info_2 = new Information(4,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));

        Information info_search_2 = new Information(5,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire questionnaire_2 = new Questionnaire(2, info_2, info_search_2);


        QuestionnaireController qc = new QuestionnaireController(iml_port, questionnaire_repository, req_cache_repository);
        qc.set_active_questionnaire(cur_questionnaire);
        qc.set_req_cache(new ReqCache(cur_questionnaire.get_id(), List.of()));
        Mockito.lenient().doReturn(List.of(questionnaire_1, questionnaire_2)).when(questionnaire_repository).find_interval(0, 1000);
        Mockito.lenient().doReturn(questionnaire_1).when(questionnaire_repository).findQuestionnaire(1);
        Mockito.lenient().doReturn(questionnaire_2).when(questionnaire_repository).findQuestionnaire(2);
        Mockito.lenient().doReturn(List.of()).when(questionnaire_repository).find_interval(1000, 2000);
        List<Map.Entry<Integer, Double>> mockList = List.of(
                new AbstractMap.SimpleEntry<>(1, 1.0),
                new AbstractMap.SimpleEntry<>(2, 2.0)
        );

        Mockito.lenient().doReturn(mockList).when(req_cache_repository).findAll(cur_questionnaire.get_id());

        RecManager rec_manager = new RecManager(qc);

        List<Questionnaire> req_list = rec_manager.get_friends();
        Questionnaire questionnaire_test = new Questionnaire(1, info_1, info_search_1);
        assertEquals(req_list.getFirst(), questionnaire_test);
    }

    @Test
    void get_friends_4() throws Exception {
        Information cur_info = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(2, "walking")))))));

        Information cur_info_search = new Information(1,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire cur_questionnaire = new Questionnaire(0, cur_info, cur_info_search);

        Information info_1 = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));

        Information info_search_1 = new Information(3,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire questionnaire_1 = new Questionnaire(1, info_1, info_search_1);

        Information info_2 = new Information(4,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new ITag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));

        Information info_search_2 = new Information(5,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new ITag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<ITag>(
                                        Arrays.asList(new ITag(0, "walking"), new ITag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<ITag>(
                                        Arrays.asList(new ITag(2, "walking"), new ITag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<ITag>(Arrays.asList(new ITag(3, "sleeping")))))));
        Questionnaire questionnaire_2 = new Questionnaire(2, info_2, info_search_2);


        QuestionnaireController qc = new QuestionnaireController(iml_port, questionnaire_repository, req_cache_repository);
        qc.set_active_questionnaire(cur_questionnaire);
        qc.set_req_cache(new ReqCache(cur_questionnaire.get_id(), List.of()));
        Mockito.lenient().doReturn(List.of(questionnaire_1, questionnaire_2)).when(questionnaire_repository).find_interval(0, 1000);
        Mockito.lenient().doReturn(questionnaire_1).when(questionnaire_repository).findQuestionnaire(1);
        Mockito.lenient().doReturn(questionnaire_2).when(questionnaire_repository).findQuestionnaire(2);
        Mockito.lenient().doReturn(List.of()).when(questionnaire_repository).find_interval(1000, 2000);
        List<Map.Entry<Integer, Double>> mockList = List.of(
                new AbstractMap.SimpleEntry<>(1, 2.0),
                new AbstractMap.SimpleEntry<>(2, 1.0)
        );

        Mockito.lenient().doReturn(mockList).when(req_cache_repository).findAll(cur_questionnaire.get_id());

        RecManager rec_manager = new RecManager(qc);

        List<Questionnaire> req_list = rec_manager.get_friends();
        Questionnaire questionnaire_test = new Questionnaire(2, info_2, info_search_2);
        assertEquals(req_list.getFirst(), questionnaire_test);
    }
}
