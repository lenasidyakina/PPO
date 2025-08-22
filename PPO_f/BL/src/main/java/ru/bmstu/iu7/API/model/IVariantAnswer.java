package ru.bmstu.iu7.API.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public interface IVariantAnswer
{

    Long getId();
    void setId(Long id);
    void setWeight(int weight);
    void setQuestion(IQuestion question);
    IQuestion getQuestion();
    void setTag(ITag tag);
    int getWeight();
    ITag getTag();
}
