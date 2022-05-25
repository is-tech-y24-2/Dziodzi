package ru.itmo.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;
import ru.itmo.tools.PostgreSQLEnumType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="cat")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Cat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Basic
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "breed", nullable = true)
    private Breed breed;
    @Basic
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "color", nullable = true)
    private Color color;

    @Basic
    @Column(name = "owner_id", nullable = true)
    private Integer ownerId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer owner_id) {
        this.ownerId = owner_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) && Objects.equals(name, cat.name) &&
                Objects.equals(breed, cat.breed) && Objects.equals(color, cat.color) &&
                    Objects.equals(ownerId, cat.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, color, ownerId);
    }

    @Override
    public String toString(){
        return "Id: " + id + ", Name: " + name + ", Breed: " + breed +
                ", Color: " + color + ", Owner's Id: " + ownerId;
    }
}

