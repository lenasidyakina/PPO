package ru.bmstu.iu7.API.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public interface IExtendedAnswer {
    int getWeight() ;
    void setWeight(int weight);
    String getAnswer();
    void setAnswer(String answer);
    List<? extends ITag> getTags();
    void setTags(List<? extends ITag> tags);
    IQuestion getQuestion();
    void setQuestion(IQuestion question);
    void setId(Long id);
    Long getId();
}
