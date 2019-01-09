package com.gce.weatherforecastapp.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "forecast_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HomeLocation homeLocation;

    @ElementCollection
    private List<String> favoriteCities;

    public List<String> getFavoriteCities() {
        return favoriteCities;
    }

    public void setFavoriteCities(List<String> favoriteCities) {
        this.favoriteCities = favoriteCities;
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public HomeLocation getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(HomeLocation homeLocation) {
        this.homeLocation = homeLocation;
    }

    public void addFavoriteCity(String favoriteCity) {
        if (favoriteCity != null) {
            if (favoriteCities == null) {
                favoriteCities = new ArrayList<>();
            }
            favoriteCities.add(favoriteCity);
        }
    }


    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof User) {
            User u = (User) obj;

            if (getUserName().equals(u.getUserName())) {
                equals = true;
            } else {
                equals = false;
            }
        }
        return equals;
    }
}
