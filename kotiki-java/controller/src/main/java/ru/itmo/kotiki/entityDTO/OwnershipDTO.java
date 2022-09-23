package ru.itmo.kotiki.entityDTO;

public class OwnershipDTO {

    private final Integer ownerId;
    private final Integer catId;

    public OwnershipDTO(Integer ownerId, Integer catId) {
        this.ownerId = ownerId;
        this.catId = catId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public Integer getCatId() {
        return catId;
    }
}
