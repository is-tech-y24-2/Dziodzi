package ru.itmo.kotiki.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.entity.Ownership;
import ru.itmo.kotiki.entityDTO.OwnerDTO;
import ru.itmo.kotiki.entityDTO.OwnershipDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class OwnerConverter  {
        @Autowired
        public OwnerConverter() {
        }

    public Owner convertToOwner(OwnerDTO ownerDto) {
        return new Owner(ownerDto.getName());
    }

    public OwnerDTO convertToOwnerDTO(Owner owner) {
        return new OwnerDTO(owner.getName());
    }

    public List<OwnerDTO> convertListOfOwners(List<Owner> owners) {
        List<OwnerDTO> ownerDTOs = new ArrayList<>();
        for (Owner owner : owners) {
            ownerDTOs.add(convertToOwnerDTO(owner));
        }
        return ownerDTOs;
    }

    public Ownership convertToOwnership(OwnershipDTO ownershipDto) {
        return new Ownership(ownershipDto.getOwnerId(), ownershipDto.getCatId());
    }

    public OwnershipDTO convertToOwnershipDTO(Ownership ownership) {
        return new OwnershipDTO(ownership.getOwnerId(), ownership.getCatId());
    }

    public List<OwnershipDTO> convertListOfOwnerships(List<Ownership> ownerships) {
        List<OwnershipDTO> ownershipDTOs = new ArrayList<>();
        for (Ownership ownership : ownerships) {
            ownershipDTOs.add(convertToOwnershipDTO(ownership));
        }
        return ownershipDTOs;
    }
}
