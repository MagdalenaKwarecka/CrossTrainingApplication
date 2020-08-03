package com.kvarek.service;

public interface SecurityService {
    String findLoggedInLogin();

    void autoLogin(String login, String password);


}
