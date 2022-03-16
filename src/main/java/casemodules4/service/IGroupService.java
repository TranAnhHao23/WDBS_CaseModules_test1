package casemodules4.service;

import casemodules4.model.Group;

import java.util.List;

public interface IGroupService extends IGeneralService<Group> {
    Iterable<Group> findAllByName(String name);
}
