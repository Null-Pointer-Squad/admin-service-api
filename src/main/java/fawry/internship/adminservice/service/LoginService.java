package fawry.internship.adminservice.service;

import fawry.internship.adminservice.model.LoginRequest;
import fawry.internship.adminservice.model.LoginResponse;

public interface LoginService {
    public LoginResponse authenticate(LoginRequest request);
}
