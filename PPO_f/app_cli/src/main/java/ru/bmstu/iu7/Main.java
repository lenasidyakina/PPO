package ru.bmstu.iu7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.bmstu.iu7.API.model.*;
import ru.bmstu.iu7.impl.*;
import ru.bmstu.iu7.src.MainManager;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        //SpringUserRepository springUserRepository = context.getBean(SpringUserRepository.class);
        //SpringQuestionnaireRepository springQuestionnaireRepository = context.getBean(SpringQuestionnaireRepository.class);


        DBAPI api = new DBAPI();
        api.m_extendedAnswerRepository = context.getBean(SpringExtendedAnswerRepository.class);
        api.m_questionnaireRepository = context.getBean(SpringQuestionnaireRepository.class);
        api.m_variantAnswerRepository = context.getBean(SpringVariantAnswerRepository.class);
        api.m_userRepository = context.getBean(SpringUserRepository.class);
        api.m_informationRepository = context.getBean(SpringInformationRepository.class);
        api.m_questionRepository = context.getBean(SpringQuestionRepository.class);
        api.m_variantAnswerRepository = context.getBean(SpringVariantAnswerRepository.class);

        UserRepository userRepository = new UserRepository(api.m_userRepository);

        QuestionnaireRepository questionnaireRepository = new QuestionnaireRepository(api);

        MainManager m = new MainManager(new ML_port(),userRepository, questionnaireRepository, new ReqCacheRepository(), new QuestionRepository());
        IUser user = m.getM_user_manager().authorize("Lena", "Lena12345");

        // Tags
        ITag walking_tag = ModelFactory.makeTag(0L, "walking");
        ITag watching_tag = ModelFactory.makeTag(3L, "watching TV");
        ITag swimming_tag =ModelFactory.makeTag(1L, "swimming");
        ITag sleeping_tag = ModelFactory.makeTag(2L, "sleeping");

        // Questions
        IQuestion question_swimming = ModelFactory.makeQuestion(0L,
                "Do you love swimming or watching TV?",
                new ArrayList<ITag>(Arrays.asList(watching_tag, swimming_tag)), false);
        IQuestion question_time =  ModelFactory.makeQuestion(1L,
                "How do you like to spend your time? ",
                new ArrayList<ITag>(Arrays.asList(walking_tag, sleeping_tag)), true);

        // VariantAnswer
        IVariantAnswer variantAnswer_1 =  ModelFactory.makeVariantAnswer(1L,1, watching_tag, question_swimming);
        IVariantAnswer variantAnswer_2 = ModelFactory.makeVariantAnswer(3L, 2, swimming_tag, question_swimming);

        // ExtendedAnswer
        IExtendedAnswer extendedAnswer_dog = ModelFactory.makeExtendedAnswer(1L, question_time,2,
                "I like walking my dog.", new ArrayList<ITag>());
        IExtendedAnswer extendedAnswer_sleep = ModelFactory.makeExtendedAnswer(2L, question_time ,2,
                "I like walking my dog and sleeping", new ArrayList<ITag>());

        IInformation info1 = ModelFactory.makeInformation(1L,
                new ArrayList<IVariantAnswer>(Arrays.asList(variantAnswer_1)),
                new ArrayList<IExtendedAnswer>(Arrays.asList(extendedAnswer_dog))
        );

        IInformation info2 = ModelFactory.makeInformation(2L,
                new ArrayList<IVariantAnswer>(Arrays.asList(variantAnswer_2)),
                new ArrayList<IExtendedAnswer>(Arrays.asList(extendedAnswer_sleep))
        );
        ArrayList<IQuestion> questions = new ArrayList<IQuestion>();
        questions.add(question_swimming);
        questions.add(question_time);

        IQuestionnaire questionnaire = m.getM_que_manager().create(user, info1, info2);
    }

}


/*
import ru.bmstu.iu7.API.IML_port;
import ru.bmstu.iu7.API.model.Tag;
import ru.bmstu.iu7.src.MainManager;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        IML_port port = new ML_port();
        port.get_tags_names("How do you like to spend your time? ","I like walking my dog, and in the evening I go out to clubs. I also work out actively and love my leg. I like watching movies.", List.of(new Tag(1, "Walking"), new Tag(1, "Watching movies"), new Tag(2, "Leggings")));
        MainManager m = new MainManager(new ML_port(), new UserRepository(), new QuestionnaireRepository(), new ReqCacheRepository());
        // User user = m.register("Lena", "Lena12345");
    }
}
*/