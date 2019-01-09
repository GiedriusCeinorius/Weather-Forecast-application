package com.gce.weatherforecastapp.controller;

import com.gce.weatherforecastapp.DAO.UsersRepository;
import com.gce.weatherforecastapp.model.User;
import com.gce.weatherforecastapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/add/User")
    public String addUser(@RequestBody User userInfo) {
        User user = userRepository.findByUserName(userInfo.getUserName());
        if (user == null) {
            userRepository.save(userInfo);
            return "You just added a user!";
        } else {
            return "User with user name " + userInfo.getUserName() + " already exists!";
        }
    }

    @PutMapping("/update/User")
    public String updateUser(@RequestBody User userInfo) {
        return userService.updateCurrentUser(userInfo);
    }


    @GetMapping("/get/UserByUserName/{username}")
    public User getUser(@PathVariable String username) {
        return userRepository.findByUserName(username);
    }

    @PutMapping("/remove/UserInformation/{user}")
    public String removeUserInfo(@PathVariable String user) {
        return userService.removeUserInformation(user);
    }

    @GetMapping("/get/AllUsers")
    public List<User> getUsers(@AuthenticationPrincipal final UserDetails userDetails) {

        List<User> list = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            list.add(user);
        }
        return list;
    }

    @GetMapping("/get/UsersByFirstName/{firstName}")
    public List<User> getUserByFirstName(@PathVariable String firstName) {
        List<User> list = userRepository.findAllByFirstName(firstName);
        return list;
    }

    @GetMapping("/get/UsersByLastName/{lastName}")
    public List<User> getUserByLastName(@PathVariable String lastName) {
        List<User> list = userRepository.findAllByLastName(lastName);
        return list;
    }

    @GetMapping("/get/UsersByFavoriteCity/{favoriteCity}")
    public List<User> getUsersByFavoriteCity(@PathVariable String favoriteCity) {
        List<User> list = new ArrayList<>();
        OUTER:
        for (User user : userRepository.findAll()) {
            INNER:
            for (String s : user.getFavoriteCities()) {
                if (favoriteCity.equals(s)) {
                    list.add(user);
                    break INNER;
                }
            }
        }
        return list;
    }
}
