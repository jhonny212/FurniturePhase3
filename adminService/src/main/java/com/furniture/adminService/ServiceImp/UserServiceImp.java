package com.furniture.adminService.ServiceImp;

import com.furniture.adminService.Model.Profile;
import com.furniture.adminService.Repository.UserRepository;
import com.furniture.adminService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Profile createUser(Profile profile) {
        try {
            return userRepository.save(profile);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Page<Profile> getAllUsers(Optional<Integer> page) {
        Page<Profile> list= this.userRepository.findAllByUsernameContainsAndStatusEquals(
                "",true,
                PageRequest.of(
                        page.orElse(0),2
                )
        );
        return list;
    }
    @Override
    public Page<Profile> FilterAllUsers(Optional<Integer> page, Optional<String> name, Optional<Integer> rol){
        if(rol.isPresent()){
            if(rol.get()==-1){
                return this.userRepository.findAllByUsernameContainsAndStatusEquals(
                        name.orElse(""),true,
                        PageRequest.of(
                                page.orElse(0),5
                        )
                );
            }
            return this.userRepository.findAllByUsernameContainsAndUserTypeEqualsAndStatusEquals(
                    name.orElse(""),rol.orElse(0),true,
                    PageRequest.of(
                            page.orElse(0),5
                    )
            );
        }else{
            return this.userRepository.findAllByUsernameContainsAndStatusEquals(
                    name.orElse(""),true,
                    PageRequest.of(
                            page.orElse(0),5
                    )
            );
        }

    }

    @Override
    public boolean deleteUser(Integer id) {
        try {
            Optional<Profile> op = userRepository.findById(id);
            if(op.isPresent()){
                Profile pf = op.get();
                pf.setStatus(false);
                userRepository.save(pf);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
