package casemodules4.repository;

import casemodules4.model.FriendList;
import casemodules4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IFriendListRepository extends JpaRepository<FriendList, Long> {

    FriendList deleteByUserFromAndUserTo(User userFrom, User userTo);

    @Query(value = "select * from friend_list where status = 'friend' and user_from_id_user =:idUser or user_to_id_user = :idUser", nativeQuery = true )
    List<FriendList> findFriendListByIdUser(@Param("idUser") Long idUser);


    @Modifying
    @Query(value = "UPDATE friend_list SET status = 'friend' WHERE user_from_id_user = :userFromId AND user_to_id_user = :userToId", nativeQuery = true)
    void acceptFriendRequest(@Param("userFromId") Long userFromId,@Param("userToId") Long userToId);

    @Modifying
    @Query(value = "UPDATE friend_list SET status = 'block' WHERE user_from_id_user = :userFromId AND user_to_id_user = :userToId", nativeQuery = true)
    void blockFriendRequest(Long userFromId, Long userToId);

}
