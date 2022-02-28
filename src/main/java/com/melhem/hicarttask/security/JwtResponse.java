package com.melhem.hicarttask.security;

//jwt
public class JwtResponse {

    private String token;
private  String email ;
private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JwtResponse(String token, String email ,String name) {
        this.token = token;
        this.email= email;
        this.name= name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
