package casemodules4.service.impl;

import casemodules4.model.Group;
import casemodules4.model.GroupMembers;
import casemodules4.model.Post;
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

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(Long idGroup) {
        if (groupRepository.findById(idGroup).isPresent()){
            return groupRepository.findById(idGroup).get();
        }
        return null;
    }

    @Override
    public int getTotalMembers(Long idGroup) {
        return groupRepository.getTotalMembers(idGroup);
    }

    @Override
    public List<GroupMembers> findAllByIdGroup(Long idGroup) {
        return groupMembersRepository.findAllById_IdGroup(idGroup);
    }

    @Override
    public List<Long> getPostInGroup(Long idGroup) {
        return groupRepository.getPostInGroup(idGroup);
    }

    @Override
    public void createNewPostInGroup(Long idGroup, Long idPost) {
        groupRepository.createNewPostInGroup(idGroup, idPost);
    }

    @Override
    public GroupMembers findAllById_IdGroupAndId_IdUser(Long idGroup, Long idUser) {
        return groupMembersRepository.findAllById_IdGroupAndId_IdUser(idGroup, idUser);
    }
}
