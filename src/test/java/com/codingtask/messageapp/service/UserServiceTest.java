package com.codingtask.messageapp.service;

import com.codingtask.messageapp.model.User;
import com.codingtask.messageapp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    @DisplayName("Should save a new user to database")
    void shouldSaveNewUserToDb() {
        User newUser = new User(0L , "testUser1");
        User newUserFromDb = new User(1L, "testUser1");

        Mockito.when(userRepository.save(newUser)).thenReturn(newUserFromDb);

        userService.saveUser(newUser);

        Mockito.verify(userRepository, Mockito.times(1)).save(userArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should return an existing user by id from database")
    void shouldReturnUserByIdFromDb() {
        User expectedUser = new User(1L, "testUser1");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

        User userFromDb = userService.getUserById(1L);

        Assertions.assertThat(userFromDb.getId()).isEqualTo(expectedUser.getId());
        Assertions.assertThat(userFromDb.getName()).isEqualTo(expectedUser.getName());
    }

    @Test
    @DisplayName("Should return an existing user by name from database")
    void shouldReturnUserByNameFromDb() {
        User expectedUser = new User(1L, "testUser1");

        Mockito.when(userRepository.findByName("testUser1")).thenReturn(Optional.of(expectedUser));

        User userFromDb = userService.getUserByName("testUser1");

        Assertions.assertThat(userFromDb.getId()).isEqualTo(expectedUser.getId());
        Assertions.assertThat(userFromDb.getName()).isEqualTo(expectedUser.getName());
    }

    @Test
    @DisplayName("Should return all users from database")
    void shouldReturnAllUsersFromDb() {
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "testUser1"),
                new User(2L, "testUser2"),
                new User(3L, "testUser3")
        );

        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> usersFromDb = userService.getAllUsers();

        Assertions.assertThat(usersFromDb).isNotNull();
        Assertions.assertThat(usersFromDb).isNotEmpty();
        Assertions.assertThat(usersFromDb.size()).isEqualTo(expectedUsers.size());
        Assertions.assertThat(usersFromDb).isEqualTo(expectedUsers);
    }

    @Test
    @DisplayName("Should delete an existing user in database")
    void shouldDeleteUserInDb() {
        User user = new User(1L, "testUser1");

        userService.deleteUser(1L);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(user.getId());
    }
}