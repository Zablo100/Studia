package com.example.samolot.Services;

import com.example.samolot.Models.KlientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private KlientService klientService;

    @Autowired
    public LoginService(KlientService klientService) {
        this.klientService = klientService;
    }

    public boolean checkUser(String name){
        return klientService.getKlientByName(name).isPresent();
    }
}
