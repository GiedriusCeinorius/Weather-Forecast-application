package com.gce.weatherforecastapp.service;

import com.gce.weatherforecastapp.DAO.UsersRepository;
import com.gce.weatherforecastapp.model.HomeLocation;
import com.gce.weatherforecastapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public String updateCurrentUser(User user) {
        String message = null;
        User u = usersRepository.findByUserName(user.getUserName());
        if (u != null) {
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setPassword(user.getPassword());
            u.setHomeLocation(user.getHomeLocation());
            u.setFavoriteCities(user.getFavoriteCities());
            usersRepository.save(u);
            message = "You succsessfully updated your user information!";
        } else {
            message = "This user doesn't exist! Please add user first!";
        }
        return message;
    }

    public String removeUserInformation(String userName) {
        String message = null;
        User u = usersRepository.findByUserName(userName);
        if (u == null) {
            message = "This user doesn't exist!";
        } else {
            u.setUserName(userName);
            u.setFirstName("First Name");
            u.setLastName("Last Name");
            u.setPassword("Pasword");
            HomeLocation hl = new HomeLocation();
            hl.setCountry("Country");
            hl.setHouseNumber(0);
            hl.setStreet("Street");
            u.setHomeLocation(hl);
            List<String> list = new ArrayList<>();
            list.add("Favorite cities");
            u.setFavoriteCities(list);
            usersRepository.save(u);
            message = "You succsessfully removed your user information!";
        }
        return message;
    }
}
