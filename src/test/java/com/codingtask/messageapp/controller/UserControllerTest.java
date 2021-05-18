package com.codingtask.messageapp.controller;

import com.codingtask.messageapp.model.User;
import com.codingtask.messageapp.service.UserService;
import com.codingtask.messageapp.util.UserUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

//@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private UserUtil userUtil;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @DisplayName("Should return all users for the endpoint - GET /api/users")
//    void shouldReturnAllUsers() throws Exception {
//
//        User user1 = new User(1L, "testUser1");
//        User user2 = new User(2L, "testUser2");
//        User user3 = new User(3L, "testUser3");
//
//        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2, user3));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1L)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("testUser1")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2L)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("testUser2")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3L)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("testUser3")));
//    }
}