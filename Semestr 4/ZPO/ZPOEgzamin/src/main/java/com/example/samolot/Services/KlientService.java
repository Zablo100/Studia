package com.example.samolot.Services;

import com.example.samolot.Models.KlientModel;
import com.example.samolot.Repository.KlientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlientService {

    private KlientRepository klientRepository;

    @Autowired
    public KlientService(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    public Optional<KlientModel> getKlientById(Integer id){
        return klientRepository.findById(id);
    }

    public Optional<KlientModel> getKlientByName(String name){
        return klientRepository.findByName(name);
    }

    public List<KlientModel> getAllKlients(){
        return klientRepository.findAll();
    }
}
