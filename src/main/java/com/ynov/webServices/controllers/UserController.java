package com.ynov.webServices.controllers;

import com.ynov.webServices.DTOs.UserDTO;
import com.ynov.webServices.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    final ModelMapper modelMapper;

    List<User> users = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<User>> getUsers (){
        List<UserDTO> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(modelMapper.map(user, UserDTO.class));
        }
        return new ResponseEntity<>(
                users,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<User>  createUser (@RequestBody User user){
        users.add(user);
        return new ResponseEntity<>(
                user,
                HttpStatus.CREATED
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById (@PathVariable int id){
        for (User user : users){
            if (user.getId() == id){

                UserDTO userDTO = modelMapper.map(user, UserDTO.class);
                return new ResponseEntity<>(
                        userDTO,
                        HttpStatus.OK
                );
            }

        }
        Map<String, String> map = new HashMap<>();
        map.put("message", "User not found");
        return new ResponseEntity<>(
                map,
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("auth")
    public ResponseEntity<?> authentification (@RequestBody HashMap<String, String> map){
        String email = map.get("email");
        String password = map.get("password");

        for (User user : users){
            if (user.getEmail().equals(email)){
                if (user.getPassword().equals(password)){
                    Map<String, String> body = new HashMap<>();
                    body.put("message", "authentification réussie");
                    return new ResponseEntity<>(
                            body,
                            HttpStatus.OK
                    );
                }else {
                    Map<String, String> body = new HashMap<>();
                    body.put("message", "mot de passe incorrecte");
                    return new ResponseEntity<>(
                            body,
                            HttpStatus.FORBIDDEN
                    );
                }
            }
        }
        Map<String, String> body = new HashMap<>();
        body.put("message", "User not found");
        return new ResponseEntity<>(
                body,
                HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser (@RequestBody User newUser, @PathVariable int id){
        for (User user : users) {
            if (user.getId() == id) {

                if (newUser.getName() !=null || newUser.getName().equals(""))
                    user.setName(newUser.getName());
               if (newUser.getEmail() != null || newUser.getEmail().equals("") )
                   user.setEmail(newUser.getEmail());
               if (newUser.getPassword() != null || newUser.getPassword().equals(""))
                   user.setPassword(user.getPassword());

               return new ResponseEntity<>(
                        user,
                        HttpStatus.OK
                );
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("message", "user not found");
        return new ResponseEntity<>(
                map,
                HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser (@PathVariable int id){
        Map<String , String> map = new HashMap<>();
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                map.put("message", "user has bean deleted successfuly");
                return new ResponseEntity<>(
                        map,
                        HttpStatus.GONE
                );
            }
        }
        map.put("message", "user not found");
        return new ResponseEntity<>(
                map,
                HttpStatus.NOT_FOUND
        );
    }



}
