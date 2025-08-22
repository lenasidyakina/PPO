package ru.bmstu.iu7.API.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public interface IQuestionnaire {
    Long getId();
    IUser getUser();
    void setUser(IUser user);
    IInformation getInformation();
    void setInformation(IInformation information);
    IInformation getSearchInformation();
    void setSearchInformation(IInformation searchInformation);
    boolean isCensored();
    void setCensored(boolean censored);
    List<? extends IQuestionnaire> getBlackList();
    void setBlackList(List<? extends IQuestionnaire> blackList);
    List<? extends IQuestionnaire> getFavList();
    void setFavList(List<? extends IQuestionnaire> favList);
    boolean equals(Object o);
    String toString();
    void addBlack(IQuestionnaire black);
    void addFav(IQuestionnaire fav);
}
