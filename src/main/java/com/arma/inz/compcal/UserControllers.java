package com.arma.inz.compcal;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin
@Log
public class UserControllers {

//    private Map<String, String> list = new HashMap<>();
//
//    public UserControllers(){
//        this.list.put("tom", "domek");
//        this.list.put("Michał", "zje");
//        this.list.put("user", "password");
//    }
//
//    @PostMapping("/login")
//    public boolean login(@RequestBody Users user) {
//        log.info(Base64.getEncoder().encodeToString((user.getName()+":"+user.getPassword()).getBytes()));
//        return list.containsKey(user.getName()) && list.get(user.getName()).equals(user.getPassword());
//    }
//
//    @RequestMapping("/user")
//    public Principal user(HttpServletRequest request) {
//        System.out.println(request.getHeader("Authorization"));
//        String authToken = request.getHeader("Authorization")
//                .substring("Basic".length()).trim();
//        return new Principal() {
//            @Override
//            public String getName() {
//                return new String(Base64.getDecoder()
//                        .decode(authToken)).split(":")[0];
//            }
//        };
//    }


}
