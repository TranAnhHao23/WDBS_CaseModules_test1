package casemodules4.repository;

import casemodules4.model.GroupMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface IGroupMembersRepository extends JpaRepository<GroupMembers, Long> {
    Optional<GroupMembers> findByGroup_IdGroupAndUser_IdUser(Long group_idGroup, Long user_idUser);
    Iterable<GroupMembers> findAllByUser_IdUser(Long user_idUser);
    Iterable<GroupMembers> findAllByGroup_IdGroup(Long group_idGroup);
}
