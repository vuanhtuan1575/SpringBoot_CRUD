package com.vnpay.user.service.impl;

import com.vnpay.common.ultil.ResponseHandler;
import com.vnpay.role.entity.Role;
import com.vnpay.role.repository.RoleRepository;
import com.vnpay.user.dto.RequestUserDto;
import com.vnpay.user.dto.ReseponseUserDto;
import com.vnpay.user.dto.UpdateDto;
import com.vnpay.user.entity.User;
import com.vnpay.user.repository.UserRepository;
import com.vnpay.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> saveUser(RequestUserDto requestUser) {

        // If Username is exist then User doesn't create
        Optional<User> opUser = userRepository.findByUsername(requestUser.getUsername());

        if (opUser.isPresent()) {
            LOG.info("USERNAME IS EXIST - value: {}", requestUser.getUsername());
            return ResponseHandler.ResponseCommon(HttpStatus.BAD_REQUEST, "USER IS EXIST", false);
        }
        Optional<Role> opRole = roleRepository.findByName(requestUser.getRoleName());

        if (opRole.isEmpty()) {
            LOG.info("ROLE ISN'T EXIST - value: {}", requestUser.getRoleName());
            return ResponseHandler.ResponseCommon(HttpStatus.BAD_REQUEST, "ROLE ISN'T EXIST", false);
        }

        // Create a new user
        User user = new User();

        user.setUsername(requestUser.getUsername());
        user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        user.setPhone(requestUser.getPhone());
        user.setName(requestUser.getName());

        // set Role for user
        user.setRole(opRole.get());

        User saveUser = userRepository.save(user);

        LOG.info("Create user sucess - value: {}", requestUser.getRoleName());

        ReseponseUserDto reseponseUserDto = new ReseponseUserDto();
        reseponseUserDto.setName(saveUser.getName());
        reseponseUserDto.setPhone(saveUser.getPhone());
        reseponseUserDto.setUsername(saveUser.getUsername());
        reseponseUserDto.setRoleName(saveUser.getRole().getName());

        return ResponseEntity.status(HttpStatus.OK).body(reseponseUserDto);
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isEmpty()) {
            return ResponseHandler.ResponseCommon(HttpStatus.BAD_REQUEST, "Find not found User", null);
        }
        userRepository.delete(opUser.get());
        return ResponseHandler.ResponseCommon(HttpStatus.OK, "Delete is success", true);
    }

    @Override
    public ResponseEntity<Object> updateUser(Long id, UpdateDto updatDto) {
        Optional<User> opUser = userRepository.findById(id);

        if (opUser.isEmpty()) {
            return ResponseHandler.ResponseCommon(HttpStatus.BAD_REQUEST, "Find not found User", null);
        }
        // update a new user


        opUser.get().setName(updatDto.getName());
        opUser.get().setPassword(updatDto.getPassword());
        opUser.get().setPhone(updatDto.getPhone());
        // save update user
        User saveDB = userRepository.save(opUser.get());
        //respon data for user
        ReseponseUserDto reseponseUserDto = new ReseponseUserDto();
        reseponseUserDto.setName(saveDB.getName());
        reseponseUserDto.setPhone(saveDB.getPhone());
        reseponseUserDto.setUsername(saveDB.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(reseponseUserDto);

    }

    @Override
    public ResponseEntity<Object> findUserById(Long id) {
        Optional<User> opUser = userRepository.findById(id);

        if (opUser.isEmpty()) {
            return ResponseHandler.ResponseCommon(HttpStatus.BAD_REQUEST, "Find not found User", null);
        }


        ReseponseUserDto reseponseUserDto = new ReseponseUserDto();
        reseponseUserDto.setName(opUser.get().getName());
        reseponseUserDto.setPhone(opUser.get().getPhone());
        reseponseUserDto.setUsername(opUser.get().getUsername());
        reseponseUserDto.setId(opUser.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(reseponseUserDto);
    }

    @Override
    public ResponseEntity<Object> findUserAll() {
        List<User> listUser = userRepository.findAll();
        List<ReseponseUserDto> listResponse = new ArrayList<>();

        listUser.parallelStream().forEach(user -> {
            ReseponseUserDto reseponseUserDto = new ReseponseUserDto();
            //set data
            reseponseUserDto.setName(user.getName());
            reseponseUserDto.setPhone(user.getPhone());
            reseponseUserDto.setUsername(user.getUsername());
            reseponseUserDto.setRoleName(user.getRole().getName());
            reseponseUserDto.setId(user.getId());

            //add dto into listResponse
            listResponse.add(reseponseUserDto);
        });
        return ResponseEntity.status(HttpStatus.OK).body(listResponse);

    }
}