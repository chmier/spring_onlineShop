package com.example.projektKoncowy.repository;

import com.example.projektKoncowy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

User findByUsername(String username);

}
