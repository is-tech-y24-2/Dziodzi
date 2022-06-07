package ru.itmo.kotiki.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Friendship;
import ru.itmo.kotiki.entityDTO.CatDTO;
import ru.itmo.kotiki.entityDTO.FriendshipDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatConverter {
    @Autowired
    public CatConverter() {
    }

    public Cat convertToCat(CatDTO catDto) {
        return new Cat(catDto.getName(), catDto.getBreed(), catDto.getColor(), catDto.getBirthday());
    }

    public CatDTO convertToCatDTO(Cat cat) {
        return new CatDTO(cat.getName(), cat.getBreed(), cat.getColor(), cat.getBirthday());
    }

    public List<CatDTO> convertListOfCats(List<Cat> cats) {
        List<CatDTO> catDTOs = new ArrayList<>();
        for (Cat cat : cats) {
            catDTOs.add(convertToCatDTO(cat));
        }
        return catDTOs;
    }

    public Friendship convertToFriendship(FriendshipDTO friendshipDto) {
        return new Friendship(friendshipDto.getCatId(), friendshipDto.getFriendId());
    }

    public FriendshipDTO convertToFriendshipDTO(Friendship friendship) {
        return new FriendshipDTO(friendship.getCatId(), friendship.getFriendId());
    }

    public List<FriendshipDTO> convertListOfFriendships(List<Friendship> friendships) {
        List<FriendshipDTO> friendshipDTOs = new ArrayList<>();
        for (Friendship friendship : friendships) {
            friendshipDTOs.add(convertToFriendshipDTO(friendship));
        }
        return friendshipDTOs;
    }
}
