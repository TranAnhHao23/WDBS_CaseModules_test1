package casemodules4.service.impl;

import casemodules4.model.GroupMembers;
import casemodules4.service.IGroupMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GroupMembersServiceImpl implements IGroupMembersService {
    @Autowired
    private IGroupMembersService iGroupMembersService;

    @Override
    public Iterable<GroupMembers> findAll() {
        return iGroupMembersService.findAll();
    }

    @Override
    public Optional<GroupMembers> findById(Long id) {
        return iGroupMembersService.findById(id);
    }

    @Override
    public GroupMembers save(GroupMembers groupMembers) {
        return iGroupMembersService.save(groupMembers);
    }

    @Override
    public void remove(Long id) {
        iGroupMembersService.remove(id);
    }

    @Override
    public Optional<GroupMembers> findById_GroupAndId_User(Long id_group, Long id_user) {
        return iGroupMembersService.findById_GroupAndId_User(id_group,id_user);
    }
}
