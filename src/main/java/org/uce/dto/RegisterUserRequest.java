package org.uce.dto;

public class RegisterUserRequest {
    public String username;
    public String email;
    public String password;
    public String fullName;
    public String phoneNumber;
    public String address;
    public String role; // "CUSTOMER", "ADMIN", etc.
}