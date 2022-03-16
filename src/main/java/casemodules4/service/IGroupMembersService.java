package casemodules4.service;

import casemodules4.model.GroupMembers;

import java.util.Optional;

public interface IGroupMembersService extends IGeneralService<GroupMembers> {
    Optional<GroupMembers> findById_GroupAndId_User(Long id_group, Long id_user);
}
