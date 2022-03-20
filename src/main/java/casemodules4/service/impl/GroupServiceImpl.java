package casemodules4.service.impl;

import casemodules4.model.Group;
import casemodules4.repository.IGroupMembersRepository;
import casemodules4.repository.IGroupRepository;
import casemodules4.service.IGroupMembersService;
import casemodules4.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {
    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IGroupMembersRepository groupMembersRepository;

    @Override
    public List<Group> findAllByIdUser(Long idUser) {
        return groupRepository.findAllByIdUser(idUser);
    }

    @Override
    public void addToGroup(Long idGroup, Long idUser) {
        groupMembersRepository.addToGroup(idGroup, idUser);
    }

    @Override
    public void leaveToGroup(Long idGroup, Long idUser) {
        groupMembersRepository.leaveToGroup(idGroup, idUser);
    }
}
