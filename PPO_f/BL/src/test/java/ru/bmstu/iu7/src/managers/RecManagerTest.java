package ru.bmstu.iu7.src.managers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bmstu.iu7.API.IML_port;
import ru.bmstu.iu7.API.IQuestionnaireRepository;
import ru.bmstu.iu7.API.IUserRepository;
import ru.bmstu.iu7.API.model.*;
import ru.bmstu.iu7.src.controllers.QuestionnaireController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecManagerTest {

    @Mock
    IML_port iml_port;
    @Mock
    IQuestionnaireRepository questionnaire_repository;

    @Test
    void information_comparison() throws Exception {
        Information info1 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                    new VariantAnswer(2, new Tag(0, "walking"),
                        new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                    new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                            new ArrayList<Tag>(
                                    Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog.",
                            new ArrayList<Tag>(Arrays.asList(new Tag(2, "walking")))))));

        Information info2 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                    new VariantAnswer(3, new Tag(0, "swimming"),
                        new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))))));

        RecManager rec_manager = new RecManager(new QuestionnaireController(iml_port, questionnaire_repository));
        int coeff = rec_manager.information_comparison(info1, info2);
        assertEquals(coeff, 2);
    }

    @Test
    void information_comparison_2() throws Exception {
        Information info1 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<Tag>(Arrays.asList(new Tag(2, "walking")))))));

        Information info2 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));

        RecManager rec_manager = new RecManager(new QuestionnaireController(iml_port, questionnaire_repository));
        int coeff = rec_manager.information_comparison(info1, info2);
        assertEquals(coeff, 0);
    }

    @Test
    void recommended_questionnaires() throws Exception {
        Information cur_info = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<Tag>(Arrays.asList(new Tag(2, "walking")))))));

        Information cur_info_search = new Information(1,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));
        Questionnaire cur_questionnaire = new Questionnaire(0, cur_info, cur_info_search);

        Information info = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<Tag>(Arrays.asList(new Tag(2, "walking")))))));

        Information info_search = new Information(3,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));
        Questionnaire questionnaire = new Questionnaire(1, info, info_search);
        RecManager rec_manager = new RecManager(new QuestionnaireController(iml_port, questionnaire_repository));
        //List<Questionnaire> recommendations = rec_manager.recommended_questionnaires(cur_questionnaire, List.of(questionnaire));
        rec_manager.recommended_questionnaires(cur_questionnaire, List.of(questionnaire));
        Questionnaire questionnaire_test = new Questionnaire(1, info, info_search);
        //assertEquals(((rec_manager.get_req_cache()).getFirst()) , questionnaire_test);
        assertEquals(rec_manager.get_req_cache().getFirst().getValue(), questionnaire_test);
    }

    @Test
    void recommended_questionnaires_2() throws Exception {
        Information cur_info = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<Tag>(Arrays.asList(new Tag(2, "walking")))))));

        Information cur_info_search = new Information(1,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));
        Questionnaire cur_questionnaire = new Questionnaire(0, cur_info, cur_info_search);

        Information info_1 = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));

        Information info_search_1 = new Information(3,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));
        Questionnaire questionnaire_1 = new Questionnaire(1, info_1, info_search_1);

        Information info_2 = new Information(4,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));

        Information info_search_2 = new Information(5,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));
        Questionnaire questionnaire_2 = new Questionnaire(2, info_2, info_search_2);

        RecManager rec_manager = new RecManager(new QuestionnaireController(iml_port, questionnaire_repository));
        //List<Questionnaire> recommendations = rec_manager.recommended_questionnaires(cur_questionnaire, List.of(questionnaire_1, questionnaire_2));
        Questionnaire questionnaire_test = new Questionnaire(1, info_1, info_search_1);
        rec_manager.recommended_questionnaires(cur_questionnaire, List.of(questionnaire_1, questionnaire_2));
        //assertEquals(((rec_manager.get_req_cache()).getFirst()) , questionnaire_test);
        assertEquals(rec_manager.get_req_cache().getFirst().getValue(), questionnaire_test);
        //assertEquals(recommendations.getFirst() , questionnaire_test);
    }

    @Test
    void information_comparison_3() throws Exception {
        Information info1 = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));

        Information info2 = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));

        RecManager rec_manager = new RecManager(new QuestionnaireController(iml_port, questionnaire_repository));
        int coeff = rec_manager.information_comparison(info1, info2);
        assertEquals(coeff, 5);
    }

    @Test
    void recommended_questionnaires_3() throws Exception {
        Information cur_info = new Information(0,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<Tag>(Arrays.asList(new Tag(2, "walking")))))));

        Information cur_info_search = new Information(1,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));
        Questionnaire cur_questionnaire = new Questionnaire(0, cur_info, cur_info_search);

        Information info_1 = new Information(2,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));

        Information info_search_1 = new Information(3,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));
        Questionnaire questionnaire_1 = new Questionnaire(1, info_1, info_search_1);

        Information info_2 = new Information(4,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(2, new Tag(0, "walking"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog.",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));

        Information info_search_2 = new Information(5,
                new ArrayList<VariantAnswer>(Arrays.asList(
                        new VariantAnswer(3, new Tag(0, "swimming"),
                                new Question(0, "Do you love walking or swimming?", new ArrayList<Tag>(
                                        Arrays.asList(new Tag(0, "walking"), new Tag(1, "swimming"))))))),
                new ArrayList<ExtendedAnswer>(Arrays.asList(
                        new ExtendedAnswer(new Question(1, "How do you like to spend your time? ",
                                new ArrayList<Tag>(
                                        Arrays.asList(new Tag(2, "walking"), new Tag(3, "sleeping")))) ,2, "I like walking my dog and sleeping",
                                new ArrayList<Tag>(Arrays.asList(new Tag(3, "sleeping")))))));
        Questionnaire questionnaire_2 = new Questionnaire(2, info_2, info_search_2);

        RecManager rec_manager = new RecManager(new QuestionnaireController(iml_port, questionnaire_repository));
        rec_manager.recommended_questionnaires(cur_questionnaire, List.of(questionnaire_2, questionnaire_1));
        //List<Questionnaire> recommendations = rec_manager.recommended_questionnaires(cur_questionnaire, List.of(questionnaire_2, questionnaire_1));
        Questionnaire questionnaire_test = new Questionnaire(1, info_1, info_search_1);
        //assertEquals(recommendations.getFirst() , questionnaire_test);
        assertEquals(rec_manager.get_req_cache().getFirst().getValue(), questionnaire_test);
    }
}