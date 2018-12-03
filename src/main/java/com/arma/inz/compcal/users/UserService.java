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
    public BaseUser findUserByEmail(@RequestBody BaseUser user) {
        return usersRepository.findByEmail(user.getEmail());
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserRegistrationDTO user) {
        BaseUser entity = new BaseUser();
        BeanUtils.copyProperties(user, entity);
        entity.setHash(Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes()));
        entity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        entity.setActive(Boolean.TRUE);
        entity.setRoles(RolesEnum.USER);
        BaseUser save = usersRepository.save(entity);
        return new ResponseEntity( save != null, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public String login(@RequestBody UserLoginDTO user) {
        String hash = Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes());
        BaseUser entity = usersRepository.findOneByHash(hash);
        if (entity != null) {
            return entity.getHash();
        } else return null;
    }

}