package com.nicodev.user_service.controller;

import com.nicodev.user_service.dto.UserDTO;
import com.nicodev.user_service.model.User;
import com.nicodev.user_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    // POST
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.created(URI.create("/users/save")).body("User saved successfully");
    }

    // GET ALL
    @GetMapping("/find")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    // FIND BY ID
    @GetMapping("/find/{user_id}")
    public ResponseEntity<User> findById(@PathVariable Long user_id){
        return ResponseEntity.ok(userService.findById(user_id));
    }

    // EDIT
    @PutMapping("/edit/{user_id}")
    public ResponseEntity<User> editUser(@PathVariable Long user_id,
                                         @RequestBody User newUser){
        return ResponseEntity.ok(userService.editUser(user_id,newUser));
    }

    // PATCH
    @PatchMapping("/patch/{user_id}")
    public ResponseEntity<User> patchUser(@PathVariable Long user_id,
                                          @RequestBody User newUser){
        return ResponseEntity.ok(userService.patchUser(user_id,newUser));
    }

    // DELETE
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long user_id){
        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }

    // FIND USER DTO
    @GetMapping("/find/dto/{user_id}")
    public ResponseEntity<UserDTO> finfUserDTO(@PathVariable Long user_id){
        return ResponseEntity.ok(userService.findUserDTO(user_id));
    }

    // USER EXIST
    @GetMapping("/exist/{user_id}")
    public ResponseEntity<Boolean> userExist(@PathVariable Long user_id){
        return ResponseEntity.ok(userService.userExist(user_id));
    }


}
