package ru.bmstu.iu7.impl.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import ru.bmstu.iu7.API.model.IExtendedAnswer;
import ru.bmstu.iu7.API.model.IQuestion;
import ru.bmstu.iu7.API.model.ITag;

import java.util.List;

@Entity
public class ExtendedAnswer implements IExtendedAnswer {
    @Id
    Long id;

    @ManyToOne()
    Question question;

    int weight;
    String answer;

    @OneToMany()
    List<Tag> tags;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public List<? extends ITag> getTags() {
        return tags;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setTags(List<? extends ITag> tags) {

        this.tags = (List<Tag>)tags;
    }

    @Override
    public IQuestion getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(IQuestion question) {
        this.question = (Question) question;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
