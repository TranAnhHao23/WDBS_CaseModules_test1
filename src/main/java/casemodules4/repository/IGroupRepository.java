package casemodules4.repository;

import casemodules4.model.Group;
import casemodules4.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IGroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "select group_team.id_group,name, img_url from group_team inner join group_members on group_team.id_group = group_members.id_group where id_user = :idUser", nativeQuery = true)
    List<Group> findAllByIdUser(Long idUser);

    @Query(value = "select count(id_group) from group_members where id_group = :idGroup", nativeQuery = true)
    int getTotalMembers(@Param("idGroup") Long idGroup);

    @Query(value = "select post.id_post,content, img_url,user_post_id_user, status from group_post inner join post on post.id_post = group_post.id_post where id_group = :idGroup", nativeQuery = true)
    List<Long> getPostInGroup(@Param("idGroup") Long idGroup);

    @Modifying
    @Query(value = "INSERT INTO group_post (id_group, id_post) VALUES (:idGroup, :idPost)", nativeQuery = true)
    void createNewPostInGroup(@Param("idGroup") Long idGroup, @Param("idPost") Long idPost);
}
