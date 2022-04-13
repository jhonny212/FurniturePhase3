package com.furniture.gatewayService.Repository;

import com.furniture.gatewayService.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Profile,Integer> {

    public Profile findByUsername(String username);

}
