package com.furniture.gatewayService.Service;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface AuthenticationService {

    public ResponseEntity<HashMap<String, String>> login(String username, String password);
    public ResponseEntity<Boolean> isAdminLoggedIn(String token);
    public ResponseEntity<Boolean> isSalesManLoggedIn(String token);
    public ResponseEntity<Boolean> isFabricateManLoggedIn(String token);
    public ResponseEntity<Boolean> isLoggedIn(String token);

}
