package org.springproject.service;

import org.springproject.dao.UserDTO;
import org.springproject.entity.User;

import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Optional<User> findById(long id);
    void deleteUser(long id);
   // Optional<User> findByName(String name);
}
