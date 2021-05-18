package com.codingtask.messageapp.service;

import com.codingtask.messageapp.model.User;
import com.codingtask.messageapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) { return userRepository.save(user); }

    public User getUserById(Long userId) { return userRepository.findById(userId).get(); }

    public User getUserByName(String userName) { return userRepository.findByName(userName).orElse(null); }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) { userRepository.deleteById(id); }
}
