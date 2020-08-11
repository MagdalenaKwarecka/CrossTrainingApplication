package com.kvarek.registration.security;

public interface SecurityService {
    String findLoggedInLogin();

    void autoLogin(String login, String password);


}
