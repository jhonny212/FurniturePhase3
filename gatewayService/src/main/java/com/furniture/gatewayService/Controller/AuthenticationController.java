package com.furniture.gatewayService.Controller;

import com.furniture.gatewayService.Model.ServiceImp.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<HashMap<String,String>> login(@RequestParam("user") String username, @RequestParam("password") String password) {
        return authenticationServiceImpl.login(username,password);
    }

    @PostMapping("/isAdminLoggedIn")
    public ResponseEntity<Boolean> isAdminLoggedIn(@RequestHeader(value = "Authorization", required=false) String token){
        return authenticationServiceImpl.isAdminLoggedIn(token);
    }

    @PostMapping("/isSalesmanLoggedIn")
    public ResponseEntity<Boolean> isSalesmanLoggedIn(@RequestHeader(value = "Authorization", required=false) String token){
        return authenticationServiceImpl.isSalesManLoggedIn(token);
    }

    @PostMapping("/isFabricatemanLoggedIn")
    public ResponseEntity<Boolean> isFabricatemanLoggedIn(@RequestHeader(value = "Authorization", required=false) String token){
        return authenticationServiceImpl.isFabricateManLoggedIn(token);
    }

    @PostMapping("/isLoggedIn")
    public ResponseEntity<Boolean> isLoggedIn(@RequestHeader(value="Authorization",required = false) String token){
        return authenticationServiceImpl.isLoggedIn(token);
    }

    @PostMapping("/verifyJWT")
    public ResponseEntity<Boolean> verifyJWT(){
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
