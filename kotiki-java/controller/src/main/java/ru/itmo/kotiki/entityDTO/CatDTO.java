package ru.itmo.kotiki.entityDTO;

import ru.itmo.kotiki.enums.Breed;
import ru.itmo.kotiki.enums.Color;

import java.sql.Timestamp;

public class CatDTO {
    private final String name;
    private final Breed breed;
    private final Color color;
    private final Timestamp birthday;

    public CatDTO(String name, Breed breed, Color color, Timestamp birthday) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }


    public Breed getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }

    public Timestamp getBirthday() {
        return birthday;
    }
}
