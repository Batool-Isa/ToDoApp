package com.ga.ToDoApp.repository;

import com.ga.ToDoApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // for registration
    boolean existsByEmailAddress(String email);
    //for login
    User findUserByEmailAddress(String email);


}
