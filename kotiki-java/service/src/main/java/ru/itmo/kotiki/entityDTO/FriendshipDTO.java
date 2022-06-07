package ru.itmo.kotiki.entityDTO;

public class FriendshipDTO {
    private final Integer catId;

    private final Integer friendId;

    public FriendshipDTO(Integer catId, Integer friendId) {
        this.catId = catId;
        this.friendId = friendId;
    }

    public Integer getCatId() {
        return catId;
    }

    public Integer getFriendId() {
        return friendId;
    }
}
