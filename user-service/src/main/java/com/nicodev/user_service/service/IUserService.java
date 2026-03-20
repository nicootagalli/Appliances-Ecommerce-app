package com.nicodev.user_service.service;

import com.nicodev.user_service.dto.UserDTO;
import com.nicodev.user_service.model.User;

import java.util.List;

public interface IUserService {

    // CERATE
    public void saveUser(User user);

    // GET ALL
    public List<User> getUsers();

    // GET BY ID
    public User findById(Long user_id);

    // EDIT
    public User editUser(Long user_id, User newUser);

    // PATCH
    public User patchUser(Long user_id, User newUser);

    // DELETE
    public void deleteUser(Long user_id);

    // FIND DTO
    public UserDTO findUserDTO(Long user_id);

    // USER EXIST
    public Boolean userExist(Long user_id);


}
