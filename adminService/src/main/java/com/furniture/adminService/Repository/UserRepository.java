package com.furniture.adminService.Repository;

import com.furniture.adminService.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserRepository extends JpaRepository<Profile,Integer> {
    List<Profile> findAllByUsernameContains(String name);
    Page<Profile> findAllByUsernameContainsAndStatusEquals(String username,Boolean status, Pageable page);
    Page<Profile> findAllByUsernameContainsAndUserTypeEqualsAndStatusEquals(String username,Integer role,Boolean status,Pageable Page);

}
