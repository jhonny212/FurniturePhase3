package com.furniture.gatewayService;

import com.furniture.gatewayService.Model.Profile;
import com.furniture.gatewayService.Model.ServiceImp.AuthenticationServiceImpl;
import com.furniture.gatewayService.Repository.AuthenticationRepository;
import com.furniture.gatewayService.config.JWTAuthorizationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationServiceTest {

    @Mock
    AuthenticationRepository authenticationRepository;
    @InjectMocks
    AuthenticationServiceImpl authenticationService;
    @Mock
    JWTAuthorizationFilter jwt = new JWTAuthorizationFilter();

    @BeforeEach
    void setUp(){ MockitoAnnotations.openMocks(this); }

    @Test
    public void loginSuccesfull(){
        Profile profile = new Profile(1,"","","","",1);
        Mockito.when(
                authenticationRepository.findByUsername(Mockito.anyString())
        ).thenReturn(profile);
        Mockito.when(
                jwt.generateToken(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())
        ).thenReturn("");
        assertEquals(
                HttpStatus.OK,
                authenticationService.login(profile.getUsername(), profile.getPassword()).getStatusCode()
        );
    }

    @Test
    public void loginWithWrongUser(){
        Profile profile = new Profile(1,"","","","",1);
        Mockito.when(
                authenticationRepository.findByUsername(Mockito.anyString())
        ).thenReturn(null);
        assertEquals(
                HttpStatus.BAD_REQUEST,
                authenticationService.login(profile.getUsername(), profile.getPassword()).getStatusCode()
        );
    }

    @Test
    public void loginWithWrongPassword(){
        Profile profile = new Profile(1,"","","","",1);
        Mockito.when(
                authenticationRepository.findByUsername(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                HttpStatus.UNAUTHORIZED,
                authenticationService.login(profile.getUsername(),"wrongPass").getStatusCode()
        );
    }

    @Test
    public void isAdminLoggedInRight(){
        Profile profile = new Profile(1,"","","","",0);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                true,
                authenticationService.isAdminLoggedIn("anyToken").getBody()
        );
    }

    @Test
    public void isAdminLoggedInWrong(){
        Profile profile = new Profile(1,"","","","",1);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                false,
                authenticationService.isAdminLoggedIn("anyToken").getBody()
        );
    }

    @Test
    public void isAdminLoggedWithWrongToken(){
        assertEquals(
                HttpStatus.BAD_REQUEST,
                authenticationService.isAdminLoggedIn(null).getStatusCode()
        );
    }

    @Test
    public void isSalesManLoggedInRight(){
        Profile profile = new Profile(1,"","","","",1);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                true,
                authenticationService.isSalesManLoggedIn("anyToken").getBody()
        );
    }

    @Test
    public void isSalesManLoggedInWrong(){
        Profile profile = new Profile(1,"","","","",2);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                false,
                authenticationService.isSalesManLoggedIn("anyToken").getBody()
        );
    }

    @Test
    public void isSalesManLoggedWithWrongToken(){
        assertEquals(
                HttpStatus.BAD_REQUEST,
                authenticationService.isSalesManLoggedIn(null).getStatusCode()
        );
    }

    @Test
    public void isFabricateManLoggedInRight(){
        Profile profile = new Profile(1,"","","","",2);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                true,
                authenticationService.isFabricateManLoggedIn("anyToken").getBody()
        );
    }

    @Test
    public void isFabricateManLoggedInWrong(){
        Profile profile = new Profile(1,"","","","",0);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                false,
                authenticationService.isFabricateManLoggedIn("anyToken").getBody()
        );
    }

    @Test
    public void isFabricateManLoggedWithWrongToken(){
        assertEquals(
                HttpStatus.BAD_REQUEST,
                authenticationService.isFabricateManLoggedIn(null).getStatusCode()
        );
    }

    @Test
    public void isLoggedInWithWrongToken(){
        assertEquals(
                HttpStatus.BAD_REQUEST,
                authenticationService.isLoggedIn(null).getStatusCode()
        );
    }

    @Test
    public void isLoggedInTrue(){
        Profile profile = new Profile(1,"","","","",1);
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(profile);
        assertEquals(
                true,
                authenticationService.isLoggedIn("anyToken").getBody()
        );
    }

    @Test
    public void isLoggedInFalse(){
        Mockito.when(
                jwt.getProfileFromToken(Mockito.anyString())
        ).thenReturn(null);
        assertEquals(
                false,
                authenticationService.isLoggedIn("anyToken").getBody()
        );
    }
}
