package com.gopi.rest.webservices.restfulwebservices.user;

import com.gopi.rest.webservices.restfulwebservices.user.dao.UserDAOService;
import com.gopi.rest.webservices.restfulwebservices.user.dao.UserJPARepository;
import com.gopi.rest.webservices.restfulwebservices.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {

    @Autowired
    private UserDAOService userDAOService;

    @Autowired
    private UserJPARepository userJPARepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {

        return userJPARepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        Optional<User> user = userJPARepository.findById(id);
        if (user.isPresent() == false) {
            throw new UserNotFoundException(String.format("User with id= %d not found!!!", id));
        }
        return user.get();
    }

    @GetMapping("/jpa/users/v2/{id}")
    public EntityModel<User> retrieveUserWithHateoas(@PathVariable int id) {
        Optional<User> userOptional = userJPARepository.findById(id);
        if (userOptional.isPresent() == false) {
            throw new UserNotFoundException(String.format("User with id= %d not found!!!", id));
        }
        User user=userOptional.get();
        //Hateoas
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserJpaController.class).retrieveAllUsers());
        entityModel.add(linkBuilder.withRel("all-Users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = userJPARepository.save(user);

        //Give the uri to new user created so that user can directly use this uri to access just created user
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userJPARepository.deleteById(id);
    }

}
