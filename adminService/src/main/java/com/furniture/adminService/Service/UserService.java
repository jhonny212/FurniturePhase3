package com.furniture.adminService.Service;

import com.furniture.adminService.Model.Profile;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Profile createUser(Profile profile);
    public Page<Profile> FilterAllUsers(Optional<Integer> page, Optional<String> name, Optional<Integer> rol);
    public Page<Profile> getAllUsers(Optional<Integer> page);
    public boolean deleteUser(Integer id);
}
