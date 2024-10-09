package com.market.carmarketservice.api;

import com.market.carmarketservice.request.auth.AuthenticationRequest;
import com.market.carmarketservice.request.user.UserRequest;
import com.market.carmarketservice.response.auth.AuthenticationResponse;
import com.market.carmarketservice.request.auth.RegisterRequest;
import com.market.carmarketservice.request.valid.Username;
import com.market.carmarketservice.service.user.AuthenticationService;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3001/")
@PropertySource("classpath:notify.properties")
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final Environment env;

    @RequestMapping(value = "/auth/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(env.getProperty("NotFound"), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/auth/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("id") int id) {
        if (userService.getUser(id) != null)
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("NotFound"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        if (!userService.existUser(request.getUsername()))
            return ResponseEntity.ok(authenticationService.register(request));
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @RequestMapping(value = "/auth/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        if (userService.updateUser(user, id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/auth/user/{id}/role", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateRole(@PathVariable("id") int id, @RequestBody User user) {
        if (userService.updateRole(user, id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/auth/user/{id}/avatar", method = RequestMethod.PUT)
    public ResponseEntity<Object> changeAvatar(@PathVariable("id") int id, @RequestBody UserRequest avatar) {
        if (userService.changeAvatar(avatar, id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/auth/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        if (userService.deleteUser(id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/check-username", method = RequestMethod.POST)
    public Boolean checkUsername(@RequestBody Username username) {
        return userService.existUser(username.getUsername());
    }

    @RequestMapping(value = "/check-password", method = RequestMethod.POST)
    public Boolean checkPassword(@RequestBody AuthenticationRequest request) {
        return userService.validPassword(request);
    }

}
