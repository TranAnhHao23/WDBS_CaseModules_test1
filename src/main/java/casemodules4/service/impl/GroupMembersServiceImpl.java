package casemodules4.service.impl;

import casemodules4.model.GroupMembers;
import casemodules4.repository.IGroupMembersRepository;
import casemodules4.service.IGroupMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GroupMembersServiceImpl implements IGroupMembersService {
    @Autowired
    private IGroupMembersRepository iGroupMembersRepository;

    @Override
    public Iterable<GroupMembers> findAll() {
        return iGroupMembersRepository.findAll();
    }

    @Override
    public Optional<GroupMembers> findById(Long id) {
        return iGroupMembersRepository.findById(id);
    }

    @Override
    public GroupMembers save(GroupMembers groupMembers) {
        return iGroupMembersRepository.save(groupMembers);
    }

    @Override
    public void remove(Long id) {
        iGroupMembersRepository.deleteById(id);
    }

    @Override
    public Optional<GroupMembers> findByIdGroupAndIdUser(Long idGroup, Long idUser) {
        return iGroupMembersRepository.findByGroup_IdGroupAndUser_IdUser(idGroup,idUser);
    }
}
