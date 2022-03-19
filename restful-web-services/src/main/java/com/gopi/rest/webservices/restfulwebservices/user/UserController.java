package com.gopi.rest.webservices.restfulwebservices.user;

import com.gopi.rest.webservices.restfulwebservices.user.dao.UserDAOService;
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

@RestController
public class UserController {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDAOService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = userDAOService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id= %d not found!!!", id));
        }
        return user;
    }

    @GetMapping("/users/v2/{id}")
    public EntityModel<User> retrieveUserWithHateoas(@PathVariable int id) {
        User user = userDAOService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id= %d not found!!!", id));
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).retrieveAllUsers());
        entityModel.add(linkBuilder.withRel("all-Users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = userDAOService.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User userDeleted = userDAOService.delete(id);
        if (userDeleted == null) {
            throw new UserNotFoundException("User id-" + id
                    + " not found!!!");
        }
    }

}
