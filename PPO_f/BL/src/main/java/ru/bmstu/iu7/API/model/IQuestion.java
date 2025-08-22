package ru.bmstu.iu7.API.model;

import jakarta.persistence.*;

import java.util.List;

public interface IQuestion {
    boolean getKind();
    void setKind(boolean kind);
    Long getId();
    void setId(Long id);
    List<? extends ITag> getTags();
    void setTags(List<? extends ITag> tags);
    String getQuestion();
    void setQuestion(String question);
}
