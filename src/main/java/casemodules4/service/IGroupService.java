package casemodules4.service;

import casemodules4.model.Group;

public interface IGroupService extends IGeneralService<Group> {
    Iterable<Group> findAllByName(String name);
}
