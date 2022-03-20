package casemodules4.service;

import casemodules4.model.Group;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGroupService {

    List<Group> findAllByIdUser(Long idUser);

    void addToGroup(Long idGroup, Long idUser);

    void leaveToGroup(Long idGroup, Long idUser);


}
