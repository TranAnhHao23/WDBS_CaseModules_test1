package casemodules4.service;

import casemodules4.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);

    List<User> findAllByFullNameContaining(String name);


}
