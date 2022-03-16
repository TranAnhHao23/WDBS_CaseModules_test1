package casemodules4.service.impl;

import casemodules4.model.Role;
import casemodules4.repository.IRoleRepository;
import casemodules4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
