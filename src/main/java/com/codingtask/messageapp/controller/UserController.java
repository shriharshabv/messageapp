package com.codingtask.messageapp.controller;

import com.codingtask.messageapp.dto.UserDto;
import com.codingtask.messageapp.exception.DuplicateEntryException;
import com.codingtask.messageapp.exception.EmptyOrInvalidInputException;
import com.codingtask.messageapp.model.User;
import com.codingtask.messageapp.service.UserService;
import com.codingtask.messageapp.util.AppConstants;
import com.codingtask.messageapp.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserUtil userUtil;

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = null;
        if (userDto.getName() == null || userDto.getName().isBlank())
            throw new EmptyOrInvalidInputException(AppConstants.ERROR_CODE_601, AppConstants.INPUT_CANNOT_BE_NULL_OR_EMPTY);
        else {
            // check user exists
            User newUser = userUtil.dtoToModel(userDto);
            User userFromDb = userService.getUserByName(newUser.getName());
            if (userFromDb == null) {
                userFromDb = userService.saveUser(newUser);
                createdUser = userUtil.modelToDto(userFromDb);
                logger.info("User created successfully!");
            } else {
                throw new DuplicateEntryException(AppConstants.ERROR_CODE_602, AppConstants.SAME_NAME_ALREADY_EXISTS);
            }
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("getting all users...");
        List<UserDto> users = userUtil.modelToDto(userService.getAllUsers());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        User userFromDb = userService.getUserById(id);
        UserDto user = userUtil.modelToDto(userFromDb);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        logger.info("User deleted successfully!");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
