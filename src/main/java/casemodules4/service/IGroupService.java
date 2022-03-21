package casemodules4.service;

import casemodules4.model.Group;
import casemodules4.model.GroupMembers;
import casemodules4.model.Post;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGroupService {

    List<Group> findAllByIdUser(Long idUser);

    void addToGroup(Long idGroup, Long idUser);

    void leaveToGroup(Long idGroup, Long idUser);

    List<Group> getAll();

    Group findById(Long idGroup);

    int getTotalMembers(Long idGroup);

    List<GroupMembers> findAllByIdGroup(Long idGroup);

    List<Long> getPostInGroup(Long idGroup);

    void createNewPostInGroup(Long idGroup, Long idPost);

    GroupMembers findAllById_IdGroupAndId_IdUser(Long idGroup, Long idUser);


}
