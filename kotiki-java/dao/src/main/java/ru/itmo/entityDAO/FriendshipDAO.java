package ru.itmo.entityDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Friendship;

import java.util.List;

@Repository("friendshipDAO")
public interface FriendshipDAO extends JpaRepository<Friendship, Integer> {
    Friendship getFriendshipByCatIdAndFriendId(Integer catId, Integer friendId);
    List<Friendship> findAllByCatId(Integer id);
}
