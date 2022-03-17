package casemodules4.service.impl;

import casemodules4.model.Group;
import casemodules4.repository.IGroupRepository;
import casemodules4.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupServiceImpl implements IGroupService {
    @Autowired
    private IGroupRepository iGroupRepository;

    @Override
    public Iterable<Group> findAll() {
        return iGroupRepository.findAll();
    }

    @Override
    public Optional<Group> findById(Long id) {
        return iGroupRepository.findById(id);
    }

    @Override
    public Group save(Group group) {
        return iGroupRepository.save(group);
    }

    @Override
    public void remove(Long id) {
        iGroupRepository.deleteById(id);
    }

    @Override
    public Iterable<Group> findAllByName(String name) {
        return iGroupRepository.findAllByNameContaining(name);
    }
}
