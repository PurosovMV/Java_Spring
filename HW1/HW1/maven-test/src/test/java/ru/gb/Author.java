package ru.gb;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private int id;
    private String name;
    @Setter(AccessLevel.PROTECTED)
    private String surname;
}
