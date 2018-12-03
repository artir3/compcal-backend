package com.arma.inz.compcal.users;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Base64;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@Log
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/user/email")
    public Users findUserByEmail(@RequestBody Users user) {
        return usersRepository.findByEmail(user.getEmail());
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody Users user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(Boolean.TRUE);
        user.setRoles(RolesEnum.USER);
        user.setHash(Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes()));
        Users save = usersRepository.save(user);
        return new ResponseEntity( save != null, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public String login(@RequestBody Users user) {
        Users entity = usersRepository.findOneByEmail(user.getEmail().toLowerCase());
        String passwordToCheck = new BCryptPasswordEncoder().encode(user.getPassword());
        if (entity != null && passwordToCheck.equals(entity.getEmail())) {
            return entity.getHash();
        } else return null;
    }

}