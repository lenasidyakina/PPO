package ru.bmstu.iu7;

import ru.bmstu.iu7.API.IML_port;
import ru.bmstu.iu7.API.model.Tag;
import ru.bmstu.iu7.API.model.User;
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