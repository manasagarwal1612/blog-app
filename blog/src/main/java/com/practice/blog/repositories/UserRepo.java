package com.practice.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
