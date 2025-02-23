package org.springproject.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springproject.entity.User;
import org.springproject.dao.UserDTO;
import org.springproject.exception.UserNotFoundException;
import org.springproject.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO){
            User user = convertToEntity(userDTO);
            User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    @Cacheable(value = "getIdCache", key = "'user_' + #id")
    public Optional<User> findById(long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the database");
        }
        return user;
    }

    @Override
    public void deleteUser(long id) throws UserNotFoundException{
        Optional<User> getUser = userRepository.findById(id);
        if(getUser.isPresent()){
            getUser.ifPresent(user -> userRepository.delete(user));
        }else {
            throw new UserNotFoundException("User not found in the database");
        }
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getAddress(),
                user.getUserCreatedTimeStamp(),
                user.getUserLastUpdatedTimeStamp()
        );
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        return user;
    }

}
