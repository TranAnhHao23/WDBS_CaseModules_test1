package casemodules4.repository;

import casemodules4.model.GroupMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IGroupMembersRepository extends JpaRepository<GroupMembers, Long> {
    Optional<GroupMembers> findByGroup_IdGroupAndUserIdUser(Long idGroup, Long idUser);
}
