package ru.bmstu.iu7.impl.model;

import jakarta.persistence.*;
import ru.bmstu.iu7.API.model.IInformation;
import ru.bmstu.iu7.API.model.IQuestionnaire;
import ru.bmstu.iu7.API.model.IUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Questionnaire implements IQuestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    User user;
    //@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    //List<Question> questions;
    @OneToMany()
    List<Questionnaire> black_list;
    @OneToMany()
    List<Questionnaire> fav_list;

    @ManyToOne()
    Information information;
    @ManyToOne()
    Information search_information;
    boolean censored = Boolean.FALSE;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public IUser getUser() {
        return this.user;
    }

    @Override
    public void setUser(IUser user) {
        this.user = (User)user;
    }

    @Override
    public IInformation getInformation() {
        return this.information;
    }

    @Override
    public void setInformation(IInformation information) {
        this.information = (Information)information;
    }

    @Override
    public IInformation getSearchInformation() {
        return this.search_information;
    }

    @Override
    public void setSearchInformation(IInformation searchInformation) {
        this.search_information = (Information)searchInformation;
    }

    @Override
    public boolean isCensored() {
        return this.censored;
    }

    @Override
    public void setCensored(boolean censored) {
        this.censored = censored;
    }

    @Override
    public List<? extends IQuestionnaire> getBlackList() {
        return this.black_list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setBlackList(List<? extends IQuestionnaire> blackList) {
        this.black_list = (List<Questionnaire>)blackList;
    }

    @Override
    public List<? extends IQuestionnaire> getFavList() {
        return this.fav_list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setFavList(List<? extends IQuestionnaire> favList) {
        this.fav_list = (List<Questionnaire>)favList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questionnaire that = (Questionnaire) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return information.toString();
    }

    @Override
    public void addBlack(IQuestionnaire black) {
        this.black_list.add((Questionnaire) black);
    }

    @Override
    public void addFav(IQuestionnaire fav) {
        this.fav_list.add((Questionnaire) fav);
    }
}
