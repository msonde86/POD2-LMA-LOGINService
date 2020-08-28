package com.scb.pod2.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scb.pod2.login.model.User;

@Repository
public interface UserLoginResource extends JpaRepository<User, Integer>{
	
	@Query(value = "select * from user where username = :name and password = :pass", nativeQuery = true)
	public User findUser(@Param("name") String username, @Param("pass") String password);
	
}
