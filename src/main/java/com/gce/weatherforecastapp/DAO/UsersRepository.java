package com.gce.weatherforecastapp.DAO;

import com.gce.weatherforecastapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

    User findByUserName(String userName);

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByLastName(String lastName);

}
