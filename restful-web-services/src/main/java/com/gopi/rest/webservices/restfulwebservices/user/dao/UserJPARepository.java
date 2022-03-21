package com.gopi.rest.webservices.restfulwebservices.user.dao;

import com.gopi.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User,Integer> {
}
