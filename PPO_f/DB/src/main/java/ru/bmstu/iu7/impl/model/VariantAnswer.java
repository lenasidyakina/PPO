package ru.bmstu.iu7.impl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import ru.bmstu.iu7.API.model.IQuestion;
import ru.bmstu.iu7.API.model.ITag;
import ru.bmstu.iu7.API.model.IVariantAnswer;

@Entity
public class VariantAnswer implements IVariantAnswer
{
    @Id
    Long id;
    int weight;

    @ManyToOne(fetch = FetchType.EAGER)
    Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    Question question;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
 }

    @Override
    public void setQuestion(IQuestion question) {
        this.question = (Question)question;
    }

    @Override
    public IQuestion getQuestion() {
        return question;
 }

    @Override
    public void setTag(ITag tag) {
        this.tag = (Tag)tag;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public Tag getTag() {
        return tag;
    }
}
