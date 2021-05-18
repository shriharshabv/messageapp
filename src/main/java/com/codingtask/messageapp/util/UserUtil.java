package com.codingtask.messageapp.util;

import com.codingtask.messageapp.dto.UserDto;
import com.codingtask.messageapp.exception.EmptyOrInvalidInputException;
import com.codingtask.messageapp.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserUtil {

    public UserDto modelToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(Long.toString(user.getId()));
        userDto.setName(user.getName());
        return userDto;
    }

    public List<UserDto> modelToDto(List<User> users) {
        return users.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public User dtoToModel(UserDto userDto) {
        User user = new User();
        user.setId((userDto.getId() == null || userDto.getId().isBlank()) ? 0 : Long.parseLong(userDto.getId()));

        if (userDto.getName() == null || userDto.getName().isBlank())
            throw new EmptyOrInvalidInputException("601", "Name cannot be null or empty!");
        else user.setName(userDto.getName());

        return user;
    }

    public List<User> dtoToModel(List<UserDto> users) {
        return users.stream().map(this::dtoToModel).collect(Collectors.toList());
    }

}
