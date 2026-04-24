package com.nicodev.user_service.service;

import com.nicodev.user_service.dto.UserDTO;
import com.nicodev.user_service.exception.BadRequestException;
import com.nicodev.user_service.exception.NotFoundException;
import com.nicodev.user_service.mapper.UserMapper;
import com.nicodev.user_service.model.User;
import com.nicodev.user_service.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    // POST
    @Override
    public void saveUser(User user) {

        // Validation of required files
        if (user.getName() == null || user.getName().isEmpty()){
            throw new BadRequestException("User name required");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()){
            throw new BadRequestException("User last name required");
        }
        if (user.getDni() == null || user.getDni() <= 0){
            throw new BadRequestException("User dni required");
        }

        userRepository.save(user);
    }

    // GET ALL
    @Override
    public List<User> getUsers() {
        if (userRepository.findAll().isEmpty()) {
            throw new NotFoundException("User list empty");
        }
        return userRepository.findAll();
    }

    // FIND BY ID
    @Override
    public User findById(Long user_id) {
        return userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User with ID: " + user_id + " Not Found"));
    }

    // EDIT
    @Override
    public User editUser(Long user_id, User newUser) {
        // find the User to edit
        User userToEdit = this.findById(user_id);  // Exception already exist here.

        // set the new values
        userToEdit.setName(newUser.getName());
        userToEdit.setLastName(newUser.getLastName());
        userToEdit.setDni(newUser.getDni());

        // save the User with the new values
        this.saveUser(userToEdit);

        // return the user with the new values.
        return userToEdit;
    }

    // PATCH
    @Override
    public User patchUser(Long user_id, User newUser) {
        // find the user to edit/patch.
        User userToPatch = this.findById(user_id);   // Exception already exist here.

        // Set the new required values
        if(newUser.getName() != null) {
            userToPatch.setName(newUser.getName());
        }
        if (newUser.getLastName() != null) {
            userToPatch.setLastName(newUser.getLastName());
        }
        if (newUser.getDni() != null) {
            userToPatch.setDni(newUser.getDni());
        }

        // save the user to patch with the new values
        this.saveUser(userToPatch);

        // return the user with the new values.
        return userToPatch;
    }

    // DELETE
    @Override
    public void deleteUser(Long user_id) {
        if (!userRepository.existsById(user_id)){
            throw new NotFoundException("user with ID: " + user_id + " not found");
        }
        userRepository.deleteById(user_id);
    }

    // FIND USER DTO
    @Override
    public UserDTO findUserDTO(Long user_id) {

        User user = this.findById(user_id);  // find the user(entity)
        UserDTO dto = UserMapper.toDTO(user);  // convert to DTO using the Mapper Class

        return dto;
    }

    // USER EXIST
    @Override
    public Boolean userExist(Long user_id) {
        return userRepository.existsById(user_id);
    }
}
