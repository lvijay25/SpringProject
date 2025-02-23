package org.springproject.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springproject.dao.UserDTO;
import org.springproject.entity.User;
import org.springproject.exception.InternalServerException;
import org.springproject.exception.UserNotFoundException;
import org.springproject.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private static final Logger logging = LogManager.getLogger(UserController.class);

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUsersList(@PathVariable long id){
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseThrow(() ->
               new UserNotFoundException("User not found"));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO){
        try{
            UserDTO createUserDTO = userService.createUser(userDTO);
            Long id = createUserDTO.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(id).toUri();
            logging.info("User Created: {}", createUserDTO.toString());
            return ResponseEntity.created(location).body(createUserDTO);
        }catch(InternalServerException ex){
            logging.error("Internal Server Error", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch(InternalServerException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
