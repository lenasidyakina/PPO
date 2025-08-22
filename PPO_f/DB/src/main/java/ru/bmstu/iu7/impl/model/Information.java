package ru.bmstu.iu7.impl.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import ru.bmstu.iu7.API.model.IExtendedAnswer;
import ru.bmstu.iu7.API.model.IInformation;
import ru.bmstu.iu7.API.model.IVariantAnswer;

import java.util.List;

@Entity
public class Information implements IInformation {
    @Id
    Long id;
    @OneToMany(cascade = CascadeType.MERGE)
    List<VariantAnswer> variant_answers;
    @OneToMany(cascade = CascadeType.MERGE)
    List<ExtendedAnswer> extended_answers;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<? extends IExtendedAnswer> getExtendedAnswers() {
        return this.extended_answers;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setExtendedAnswers(List<? extends IExtendedAnswer> extendedAnswers) {
        this.extended_answers = (List<ExtendedAnswer>) extendedAnswers;
    }

    @Override
    public List<? extends IVariantAnswer> getVariantAnswers() {
        return this.variant_answers;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setVariantAnswers(List<? extends IVariantAnswer> variantAnswers) {
        this.variant_answers = (List<VariantAnswer>) variantAnswers;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
