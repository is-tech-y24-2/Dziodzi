package ru.itmo.entiity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ownership")
public class Ownership {

    public Ownership(){}

    public Ownership(Integer id, Integer ownerId, Integer catId){
        this.id = id;
        this.ownerId = ownerId;
        this.catId = catId;
    }

    public Ownership(Integer catId, Integer ownerId){
        this.ownerId = ownerId;
        this.catId = catId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;
    @Basic
    @Column(name = "cat_id", nullable = false)
    private Integer catId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ownership that = (Ownership) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(ownerId, that.ownerId) &&
                Objects.equals(catId, that.catId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, catId);
    }

    @Override
    public String toString(){
        return "Id: " + id + ", Owner Id:" + ownerId + ", Cat Id:" + catId;
    }
}
