package com.furniture.gatewayService.Model.ServiceImp;

import com.furniture.gatewayService.Model.Profile;
import com.furniture.gatewayService.Repository.AuthenticationRepository;
import com.furniture.gatewayService.Service.AuthenticationService;
import com.furniture.gatewayService.config.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;
    private JWTAuthorizationFilter jwt = new JWTAuthorizationFilter();

    @Override
    public ResponseEntity<HashMap<String, String>> login(String username, String password) {
        HashMap<String, String> body = new HashMap<>();

        Profile profile = this.authenticationRepository.findByUsername(username);
        if(profile!=null){
            if(profile.getPassword().equals(password) && profile.status){
                body.put("token",jwt.generateToken(profile.getUsername(),profile.getUserType(),profile.getId()));
                return ResponseEntity.status(HttpStatus.OK).body(body);
            }else{
                body.put("msj","La contraseña ingresada no es la correcta");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
            }
        }else{
            body.put("msj", "No se ha encontrado el usuario ingresado. ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    @Override
    public ResponseEntity<Boolean> isAdminLoggedIn(String token) {
        if(token != null){
            Profile profile = jwt.getProfileFromToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(profile.isAdmin());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @Override
    public ResponseEntity<Boolean> isSalesManLoggedIn(String token) {
        if(token != null){
            Profile profile = jwt.getProfileFromToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(profile.isSalesMan());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @Override
    public ResponseEntity<Boolean> isFabricateManLoggedIn(String token) {
        if(token != null){
            Profile profile = jwt.getProfileFromToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(profile.isFabricateMan());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @Override
    public ResponseEntity<Boolean> isLoggedIn(String token) {
        if(token != null){
            Profile profile = jwt.getProfileFromToken(token);
            if(profile != null){
                return ResponseEntity.status(HttpStatus.OK).body(true);
            }
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }
}
