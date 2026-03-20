package com.nicodev.user_service.mapper;

import com.nicodev.user_service.dto.UserDTO;
import com.nicodev.user_service.exception.BadRequestException;
import com.nicodev.user_service.model.User;

public class UserMapper {

    public static UserDTO toDTO (User user) {
        if (user == null) {
            throw new BadRequestException("User required");
        }

        return UserDTO.builder()
                .user_id(user.getUser_id())
                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .build();

    }

}
