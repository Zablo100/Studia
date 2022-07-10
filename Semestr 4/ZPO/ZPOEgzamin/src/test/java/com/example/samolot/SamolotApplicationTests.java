package com.example.samolot;

import com.example.samolot.Models.MiejsceModel;
import com.example.samolot.Services.MiejsceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class SamolotApplicationTests {

    @Autowired
    private MiejsceService service;
    @Test
    void twoSeats(){
        List<MiejsceModel> miejsca = service.getObokSiebie(2.0);
        Assert.isTrue(miejsca.get(0).getRzad() == miejsca.get(1).getRzad());
        Assert.isTrue(miejsca.get(0).getNumer() != miejsca.get(1).getNumer());
    }

    @Test
    void threeSeats(){
        List<MiejsceModel> miejsca = service.getObokSiebie(3.0);
        Assert.isTrue(miejsca.get(0).getRzad() == miejsca.get(1).getRzad());
        Assert.isTrue(miejsca.get(1).getRzad() == miejsca.get(2).getRzad());

        Assert.isTrue(miejsca.get(0).getNumer() != miejsca.get(1).getNumer());
        Assert.isTrue(miejsca.get(1).getNumer() != miejsca.get(2).getNumer());
    }

    @Test
    void fSeats(){
        List<MiejsceModel> miejsca = service.getObokSiebie(4.0);
        Assert.isTrue(miejsca.get(0).getRzad() == miejsca.get(1).getRzad());
        Assert.isTrue(miejsca.get(1).getRzad() == miejsca.get(2).getRzad());
        Assert.isTrue(miejsca.get(2).getRzad() == miejsca.get(3).getRzad());

        Assert.isTrue(miejsca.get(0).getNumer() != miejsca.get(1).getNumer());
        Assert.isTrue(miejsca.get(1).getNumer() != miejsca.get(2).getNumer());
        Assert.isTrue(miejsca.get(2).getNumer() != miejsca.get(3).getNumer());
    }



}
