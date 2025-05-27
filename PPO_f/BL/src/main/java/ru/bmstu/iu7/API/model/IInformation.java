package ru.bmstu.iu7.API.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

public interface IInformation {

    Long getId();
    void setId(Long id);
    List<? extends IExtendedAnswer> getExtendedAnswers();
    void setExtendedAnswers(List<? extends IExtendedAnswer> extendedAnswers);
    List<? extends IVariantAnswer> getVariantAnswers();
    void setVariantAnswers(List<? extends IVariantAnswer> variantAnswers);
    String toString();
}
