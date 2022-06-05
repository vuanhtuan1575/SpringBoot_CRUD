package com.vnpay.user.controller;

import com.vnpay.user.dto.RequestUserDto;
import com.vnpay.user.dto.UpdateDto;
import com.vnpay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            return userService.findUserAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody RequestUserDto requestUserDto) {
        try {
            return userService.saveUser(requestUserDto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(required = true, name = "id") Long id) {
        try {
            return userService.findUserById(id);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Update User
     *
     * @param updateDto
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateDto updateDto,
                                             @PathVariable(required = true, name = "id") Long id) {
        try {
            return userService.updateUser(id, updateDto);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Delete user by id
     *
     * @param
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(
            @PathVariable(required = true, name = "id") Long id) {
        try {
            return userService.deleteUser(id);
        } catch (Exception e) {
            return null;
        }

    }
    /**
     * Find user by id
     *
     * @param
     * @param id
     * @return
     */
    public ResponseEntity<Object> FindUserById(
            @PathVariable(required = true, name = "id") Long id){
        try{
            return userService.findUserById(id);
        }catch (Exception e) {
            return null;
        }
    }

}
