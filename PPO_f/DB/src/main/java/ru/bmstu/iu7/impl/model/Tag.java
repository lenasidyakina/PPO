package ru.bmstu.iu7.impl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.bmstu.iu7.API.model.ITag;

import java.util.Objects;

@Entity
@Table(name="tag")
public class Tag implements ITag {
    @Id
    Long id;
    String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        if (id != tag.id) return false;
        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name); // имя тега, по которому сравниваются
    }

}
