package casemodules4.service;

import casemodules4.model.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByRoleName(String roleName);
}
