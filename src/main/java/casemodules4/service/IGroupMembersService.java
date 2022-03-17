package casemodules4.service;

import casemodules4.model.GroupMembers;

import java.util.Optional;

public interface IGroupMembersService extends IGeneralService<GroupMembers> {
    Optional<GroupMembers> findByIdGroupAndIdUser(Long idGroup, Long idUser);
}
