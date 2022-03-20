package casemodules4.repository;

import casemodules4.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IGroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "select group_team.id_group,name, img_url from group_team inner join group_members on group_team.id_group = group_members.id_group where id_user = :idUser", nativeQuery = true)
    List<Group> findAllByIdUser(Long idUser);

}
