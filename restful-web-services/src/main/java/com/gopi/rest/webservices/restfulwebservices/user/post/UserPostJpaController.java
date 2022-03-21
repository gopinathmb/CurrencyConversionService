package com.gopi.rest.webservices.restfulwebservices.user.post;

import com.gopi.rest.webservices.restfulwebservices.user.User;
import com.gopi.rest.webservices.restfulwebservices.user.dao.UserJPARepository;
import com.gopi.rest.webservices.restfulwebservices.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserPostJpaController {

    @Autowired
    private UserJPARepository userJPARepository;

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable Integer id) {
        Optional<User> user = userJPARepository.findById(id);
        if (user.isPresent() == false) {
            throw new UserNotFoundException("User with id-" + id + " not found!!!");
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> savePost(@PathVariable Integer id, @RequestBody Post post) {
        Optional<User> userOptional = userJPARepository.findById(id);
        if (userOptional.isPresent() == false) {
            throw new UserNotFoundException("User with userId -" + id + " not found!!!");
        }
        User user = userOptional.get();
        post.setUser(user);

        User savedUser = userJPARepository.save(user);

        //Give the uri to new user created so that user can directly use this uri to access just created user
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
