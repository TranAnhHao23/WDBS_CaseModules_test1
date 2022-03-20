package casemodules4.repository;

import casemodules4.model.Group;
import casemodules4.model.GroupMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IGroupMembersRepository extends JpaRepository<GroupMembers, Long> {

    @Modifying
    @Query(value = "INSERT INTO group_members (id_group, id_user, role) VALUES (:idGroup, :idUser, 'user')", nativeQuery = true)
    void addToGroup(@Param("idGroup") Long idGroup, @Param("idUser") Long idUser);

    @Modifying
    @Query(value = "delete from group_members where id_group = :idGroup and id_user = :idUser", nativeQuery = true)
    void leaveToGroup(@Param("idGroup") Long idGroup, @Param("idUser") Long idUser);
}
