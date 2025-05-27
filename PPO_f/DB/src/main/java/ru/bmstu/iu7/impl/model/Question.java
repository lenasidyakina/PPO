package ru.bmstu.iu7.impl.model;

import jakarta.persistence.*;
import ru.bmstu.iu7.API.model.IQuestion;
import ru.bmstu.iu7.API.model.ITag;

import java.util.List;

@Entity
public class Question implements IQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String question;
    @OneToMany(fetch = FetchType.EAGER)
    List<Tag> tags;
    boolean kind = false;

    @Override
    public boolean getKind() {
        return kind;
    }

    @Override
    public void setKind(boolean kind) {
        this.kind = kind;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<? extends ITag> getTags() {
        return tags;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setTags(List<? extends ITag> tags) {
        this.tags = (List<Tag>) tags;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }
}
