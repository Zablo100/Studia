package com.example.samolot.Services;

import com.example.samolot.Models.KlientModel;
import com.example.samolot.Models.MiejsceModel;
import com.example.samolot.Models.RezerwacjaModel;
import com.example.samolot.Repository.KlientRepository;
import com.example.samolot.Repository.MiejsceRepository;
import com.example.samolot.Repository.RezerwacjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RezerwacjaService {

    private RezerwacjaRepository rezerwacjaRepository;
    private KlientRepository klientRepository;

    private MiejsceRepository miejsceRepository;

    @Autowired
    public RezerwacjaService(RezerwacjaRepository rezerwacjaRepository, KlientRepository klientRepository, MiejsceRepository miejsceRepository) {
        this.rezerwacjaRepository = rezerwacjaRepository;
        this.klientRepository = klientRepository;
        this.miejsceRepository = miejsceRepository;
    }

    public List<RezerwacjaModel> getAllReservations(){
        return rezerwacjaRepository.findAll();
    }

    public Optional<RezerwacjaModel> getRezerwacja(Integer id){
        return rezerwacjaRepository.findById(id);
    }

    public void addRezerwacja(String name, List<Integer> miejscaId){
        KlientModel klient;
        if (klientRepository.findByName(name).isPresent()){
            klient = klientRepository.findByName(name).stream().findFirst().orElse(null);
        }else{
            klientRepository.save(new KlientModel(name));
            klient = klientRepository.findByName(name).stream().findFirst().orElse(null);
        }

        List<RezerwacjaModel> rezerwacje = new ArrayList<>();

        List<MiejsceModel> miejsca = miejsceRepository.findAllById(miejscaId);
        miejsca.forEach(e -> e.setZarezerwowane(true));
        miejsceRepository.saveAll(miejsca);

        miejsca.forEach(m -> rezerwacje.add(new RezerwacjaModel(klient, m)));
        rezerwacjaRepository.saveAll(rezerwacje);
    }

    @Transactional
    public void deletRezerwacja(Integer id){
        rezerwacjaRepository.deleteById(id);
    }

}
