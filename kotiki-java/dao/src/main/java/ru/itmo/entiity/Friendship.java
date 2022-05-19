package ru.itmo.entiity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friendship")
public class Friendship {

    public Friendship(){
    }

    public Friendship(Integer id, Integer catId, Integer friendId){
        this.id = id;
        this.catId = catId;
        this.friendId = friendId;
    }

    public Friendship(Integer catId, Integer friendId){
        this.catId = catId;
        this.friendId = friendId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "cat_id", nullable = false)
    private Integer catId;
    @Basic
    @Column(name = "friend_id", nullable = false)
    private Integer friendId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(catId, that.catId) &&
                    Objects.equals(friendId, that.friendId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, catId, friendId);
    }

    @Override
    public String toString(){
        return "Id: " + id + ", First cat Id:" + catId + ", Second cat Id:" + friendId;
    }
}
