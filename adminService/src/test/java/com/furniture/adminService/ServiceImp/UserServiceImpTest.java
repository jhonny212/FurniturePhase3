package com.furniture.adminService.ServiceImp;

import com.furniture.adminService.Model.Profile;
import com.furniture.adminService.Repository.UserRepository;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImp userServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        Profile pf = new Profile();
        Mockito.when(
                userRepository.save(Mockito.any(Profile.class))
        ).thenReturn(pf);
        assertEquals(userServiceImp.createUser(pf),pf);
    }

    @Test
    void createUserAndThrownError(){
        Mockito.when(
                userRepository.save(Mockito.any(Profile.class))
        ).thenThrow(DataException.class);
        assertEquals(userServiceImp.createUser(new Profile()),null);
    }

    @Test
    void getAllUsers() {
        Page<Profile> list = Page.empty();
        Mockito.when(
          userRepository.findAllByUsernameContainsAndStatusEquals(
                  Mockito.anyString(),Mockito.anyBoolean(),Mockito.any(Pageable.class)
          )
        ).thenReturn(list);
        assertEquals(userServiceImp.getAllUsers(Optional.empty()),list);
    }

    @Test
    void filterAllUsers() {
        Page<Profile> list1 = Page.empty();
        Page<Profile> list2 = Page.empty();
        Mockito.when(
                userRepository.findAllByUsernameContainsAndStatusEquals(
                        Mockito.anyString(),Mockito.anyBoolean(),Mockito.any(Pageable.class)
                )
        ).thenReturn(list1);
        Mockito.when(
          userRepository.findAllByUsernameContainsAndUserTypeEqualsAndStatusEquals(
                  Mockito.anyString(),Mockito.anyInt(),Mockito.anyBoolean(),Mockito.any(Pageable.class)
          )
        ).thenReturn(list2);
        /*
        * Caso 1
        * */
        Optional<String> name = Optional.empty();
        Optional<Integer> rol = Optional.of(1);
        Optional<Integer> page = Optional.empty();
        assertEquals(userServiceImp.FilterAllUsers(
                page,name,rol
        ),list2);
        /*
        * Caso 2
        * */
        Optional<Integer> rol2 = Optional.of(-1);
        assertEquals(userServiceImp.FilterAllUsers(
                page,name,rol2
        ),list1);
        /*
        * Caso 3
        * */
        Optional<Integer> rol3 = Optional.empty();
        assertEquals(userServiceImp.FilterAllUsers(
                page,name,rol3
        ),list1);

    }

    @Test
    void deleteUser() {
        Optional<Profile> pf = Optional.of(new Profile());
        Mockito.when(
                userRepository.findById(Mockito.anyInt())
        ).thenReturn(pf);
        Mockito.when(
                userRepository.save(Mockito.any(Profile.class))
        ).thenReturn(pf.get());
        assertTrue(userServiceImp.deleteUser(1));
    }

    @Test
    void deleteUserAndReturnFalse() {
        Optional<Profile> pf = Optional.empty();
        Mockito.when(
                userRepository.findById(Mockito.anyInt())
        ).thenReturn(pf);
        assertFalse(userServiceImp.deleteUser(1));
    }

    @Test
    void deleteUserAndThrownErrorAndReturnFalse() {
        //Optional<Profile> pf = Optional.empty();
        Mockito.when(
                userRepository.findById(Mockito.anyInt())
        ).thenThrow(DataException.class);
        assertFalse(userServiceImp.deleteUser(1));
    }
}