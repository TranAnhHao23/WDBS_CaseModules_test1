package casemodules4.service.impl;

import casemodules4.model.FriendList;
import casemodules4.model.User;
import casemodules4.repository.IFriendListRepository;
import casemodules4.repository.IUserRepository;
import casemodules4.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendListServiceImpl implements IFriendListService {

    @Autowired
    private IFriendListRepository friendListRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<FriendList> findAll() {
        return friendListRepository.findAll();
    }

    @Override
    public FriendList findById(Long id) {
        if (friendListRepository.findById(id).isPresent()) {
            return friendListRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public FriendList save(FriendList friendList) {
        String status = checkFriendStatus(friendList.getUserFrom().getIdUser(), friendList.getUserTo().getIdUser());
        if (status == null) {
            friendListRepository.save(friendList);
        }
        return null;
    }

    ;

    @Override
    public void unFriend(Long userFirstId, Long userSecondId) {
        User userFrom = userRepository.getById(userFirstId);
        User userTo = userRepository.getById(userSecondId);
        String status = checkFriendStatus(userFirstId, userSecondId);
        if (status != null){
            friendListRepository.deleteByUserFromAndUserTo(userFrom, userTo);
        }
    }

    @Override
    public String checkFriendStatus(Long userFirstId, Long userSecondId) {
        String status = null;
        List<FriendList> friendLists = friendListRepository.findAll();
        for (FriendList friendList : friendLists) {
            if ((friendList.getUserFrom().getIdUser() == userFirstId && friendList.getUserTo().getIdUser() == userSecondId)
//            ||(friendList.getUserFrom().getIdUser() == userSecond && friendList.getUserTo().getIdUser() == userFirst)
            ) {
                status = friendList.getStatus();
            }
        }
        return status;
    }

    @Override
    public List<FriendList> findFriendListByIdUser(Long idUser) {
        return friendListRepository.findFriendListByIdUser(idUser);
    }

    @Override
    // Trùng phương thức
    public void sendFriendRequest(FriendList friendList) {
        String status = checkFriendStatus(friendList.getUserFrom().getIdUser(), friendList.getUserTo().getIdUser());
        if (status == null) {
            friendListRepository.save(friendList);
        }
    }

    @Override
    public void acceptFriendRequest(Long userToId, Long userFromId) {
        String status = checkFriendStatus(userToId, userFromId);
        if (status.equals("pending"))
            friendListRepository.acceptFriendRequest(userToId, userFromId);
    }

    @Override
    public void blockFriend(Long userFromId, Long userToId) {
        String status = checkFriendStatus(userFromId, userToId);
        if (status != null) {
            friendListRepository.blockFriendRequest(userFromId, userToId);
        } else {
            User userFrom = userRepository.getById(userFromId);
            User userTo = userRepository.getById(userToId);
            String statusCreate = "block";
            friendListRepository.save(new FriendList(userFrom, userTo, statusCreate));
        }
    }


}
