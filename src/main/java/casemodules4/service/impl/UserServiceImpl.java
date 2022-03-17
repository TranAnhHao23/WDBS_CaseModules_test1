package casemodules4.service.impl;

import casemodules4.model.User;
import casemodules4.repository.IUserRepository;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        if (userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllByFullNameContaining(String name) {
        return userRepository.findAllByFullNameContaining(name);
    }

    @Override
    public User findByAccount_Id(Long id) {
        return userRepository.findByAccount_Id(id);
    }
}
