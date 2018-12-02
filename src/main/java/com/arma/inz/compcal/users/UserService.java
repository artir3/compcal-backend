package com.arma.inz.compcal.users;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;

@RestController
@Log
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @PostMapping("/user/email")
    public Users findUserByEmail(@RequestBody Users user) {
        return usersRepository.findByEmail(user.getEmail());
    }

    @PostMapping("/registration")
    public void registration(@RequestBody Users user) {
        user.setPassword(new String(Base64.getEncoder().encode(user.getPassword().getBytes())));
        user.setActive(1);
        Roles userRole = rolesRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Roles>(Arrays.asList(userRole)));
        usersRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        log.info(Base64.getEncoder().encodeToString((user.getName()+":"+user.getPassword()).getBytes()));
        Users users = usersRepository.findByEmail(user.getEmail());
        if (users != null && users.getPassword().equals(user.getPassword())){
            return Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes());
        } else return null;
    }

}