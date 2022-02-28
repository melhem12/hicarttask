package com.melhem.hicarttask.security;




import com.melhem.hicarttask.repositories.DB;
import com.melhem.hicarttask.requests.RegisterRequest;
import com.melhem.hicarttask.response.Dialog;
import com.melhem.hicarttask.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*")

@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    private TokenUtil tokenUtil;


    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value = {"/login"})
    public Response<JwtResponse> signIn(@RequestBody SignInRequest signInRequest) {
        Response response= new Response();
        Dialog dialog = new Dialog();
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
                 AppUser user =(AppUser) userDetails;
        JwtResponse tokenResponse = new JwtResponse(token,userDetails.getUsername(),user.getName());

        response.setData(tokenResponse);

        dialog.setMessage(" Login Successful");
        dialog.setTitle(" Login Successful");
        response.setStatus(true);
        response.setDialog(dialog);
        return response;
    }



    @PostMapping(value = {"/register"})
    public Response<JwtResponse> register(@RequestBody RegisterRequest registerRequest) {
        Response response= new Response();
        Dialog dialog = new Dialog();
        if(userService.userExist(registerRequest.getEmail())){

            dialog.setMessage("User already exist please change your Email Address");
            dialog.setTitle(" user Exist");
              response.setDialog(dialog);
            }else{
        AppUser user = new  AppUser();
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setGender(registerRequest.getGender());
            user.setAddress(registerRequest.getAddress());

            user.setPhone(registerRequest.getPhone());

            user.setCreated(new Date());
            user.setName(registerRequest.getName());


            userService.save(user);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(registerRequest.getEmail());
        String token = tokenUtil.generateToken(userDetails);
        AppUser my = (AppUser) userDetails;
            JwtResponse tokenResponse = new JwtResponse(token,userDetails.getUsername(),my.getName());
        response.setData(tokenResponse);
            dialog.setMessage("Register Success ");
            dialog.setTitle(" Register Success");
            response.setDialog(dialog);
        response.setStatus(true);
        }
        return response;
    }




}
