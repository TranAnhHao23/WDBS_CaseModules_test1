package casemodules4.service;

import casemodules4.model.FriendList;
import casemodules4.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFriendListService {
    List<FriendList> findAll();

    FriendList findById(Long id);

    FriendList save(FriendList friendList);

    void sendFriendRequest(FriendList friendList);

    void acceptFriendRequest(Long userFromId, Long userToId);

    void blockFriend(Long userFromId, Long userToId);

    String checkFriendStatus(Long userFirstId, Long userSecondId);

    List<FriendList> findFriendListByIdUser(Long idUser);

    String checkFriendsStatus(Long idUserFrom, Long idUserTo);

    List<FriendList> findAllPendingByIdUser(Long idUser);

    void deleteByUserFrom_IdUserAndUserTo_IdUser(Long idUserFrom, Long idUserTo);

    void addFriend(Long idUserFrom, Long idUserTo);
}
