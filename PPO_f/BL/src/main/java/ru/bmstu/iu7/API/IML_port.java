package ru.bmstu.iu7.API;

import ru.bmstu.iu7.API.model.ITag;

import java.io.IOException;
import java.util.List;

public interface IML_port {
    List<ITag> get_tags_names(String question, String answer, List<ITag> tags) throws IOException, InterruptedException;
}
