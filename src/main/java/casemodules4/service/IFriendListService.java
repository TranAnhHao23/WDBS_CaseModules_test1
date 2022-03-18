package casemodules4.service;

import casemodules4.model.FriendList;
import casemodules4.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFriendListService {
    List<FriendList> findAll();

    FriendList findById(Long id);

    FriendList save(FriendList friendList);

    void unFriend(Long userFirstId, Long userSecondId);

    void sendFriendRequest(FriendList friendList);

    void acceptFriendRequest(Long userFromId, Long userToId);

    void blockFriend(Long userFromId, Long userToId);

    String checkFriendStatus(Long userFirstId, Long userSecondId);

    List<FriendList> findFriendListByIdUser(@Param("idUser") Long idUser);

    String checkFriendsStatus(Long idUserFrom, Long idUserTo);

    List<FriendList> findAllPendingByIdUser(@Param("idUser") Long idUser);
}
