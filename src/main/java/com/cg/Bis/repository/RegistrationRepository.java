package com.cg.Bis.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.Bis.model.User;



public interface RegistrationRepository extends JpaRepository<User, Integer>{
public User findByEmailId(String email);
public User findByEmailIdAndPassword(String email,String password);

}
