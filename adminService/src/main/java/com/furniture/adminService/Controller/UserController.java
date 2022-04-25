package com.furniture.adminService.Controller;

import com.furniture.adminService.Model.Profile;
import com.furniture.adminService.ServiceImp.UserServiceImp;
import com.furniture.adminService.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private Util util;

    @PostMapping
    public ResponseEntity<Profile> createUser(
            @Valid @RequestBody  Profile profile, BindingResult result,
            @RequestHeader("Authorization") String auth){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,util.formatMessage(result));
        }else{
            Profile pf = userServiceImp.createUser(profile);
            if(pf==null){
                return ResponseEntity.badRequest().body(null);
            }else {
                return  ResponseEntity.ok().body(pf);
            }
        }
    }

    @GetMapping()
    public ResponseEntity<Page<Profile>> getAllUsers(@RequestParam Optional<Integer> page,
                                     @RequestParam Optional<String> name,
                                     @RequestParam Optional<Integer> role,
                                     @RequestHeader("Authorization") String auth
    ){
        if(name.isPresent() || role.isPresent() ){
            return ResponseEntity.ok(this.userServiceImp.FilterAllUsers(page,name,role));
        }
        return ResponseEntity.ok(this.userServiceImp.getAllUsers(page));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id,
                                               @RequestHeader("Authorization") String auth){
        return ResponseEntity.ok().body(userServiceImp.deleteUser(id));
    }
}
