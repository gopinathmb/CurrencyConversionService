package com.gopi.rest.webservices.restfulwebservices.user.post.dao;

import com.gopi.rest.webservices.restfulwebservices.user.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJPARepository extends JpaRepository<Post,Integer> {
}
