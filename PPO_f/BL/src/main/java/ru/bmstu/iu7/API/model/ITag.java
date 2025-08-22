package ru.bmstu.iu7.API.model;

public interface ITag {
    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    boolean equals(Object o);
    int hashCode();
}
