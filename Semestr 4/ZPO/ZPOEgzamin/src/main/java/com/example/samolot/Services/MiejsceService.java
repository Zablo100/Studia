package com.example.samolot.Services;

import com.example.samolot.Models.KlientModel;
import com.example.samolot.Models.MiejsceModel;
import com.example.samolot.Repository.KlientRepository;
import com.example.samolot.Repository.MiejsceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class MiejsceService {

    private MiejsceRepository miejsceRepository;

    @Autowired
    public MiejsceService(MiejsceRepository miejsceRepository) {
        this.miejsceRepository = miejsceRepository;
    }

    public Optional<MiejsceModel> getMiejsce(Integer id){
        return miejsceRepository.findById(id);
    }

    public List<MiejsceModel> getMiejsca(){
        return miejsceRepository.findAll();
    }

    public List<MiejsceModel> getWolneMiejsca(){
        return miejsceRepository.findAll().stream().filter(e -> !e.isZarezerwowane()).collect(Collectors.toList());
    }

    public List<MiejsceModel> getObokSiebie(Double numberOfSeats) {

        Integer rzad = getWolneMiejsca().stream()
                .collect(Collectors.groupingBy(MiejsceModel::getRzad, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);



        if (numberOfSeats.intValue() != 5) {
            List<MiejsceModel> numery = getMiejsca()
                    .stream()
                    .filter(e -> Objects.equals(e.getRzad(), rzad)).toList();



            List<MiejsceModel> brakujace = numery.stream()
                    .filter(MiejsceModel::isZarezerwowane).toList();


            List<MiejsceModel> wolne = numery.stream().filter(m -> !m.isZarezerwowane()).toList();

            List<Integer> wolneNumer = new ArrayList<>();
            if (brakujace.size() > 0) {
                if (numberOfSeats.intValue() == 4) {
                    wolne.forEach(e ->
                    {
                        if (e.getNumer() + 1 != brakujace.get(0).getNumer()) {
                            wolneNumer.add(e.getNumer());
                        }
                    });
                }else if (numberOfSeats.intValue() == 3){
                    wolne.forEach(e ->
                    {
                        if (e.getNumer() + 1 != brakujace.get(0).getNumer() && e.getNumer() + 1 != brakujace.get(1).getNumer()) {
                            wolneNumer.add(e.getNumer());
                        }
                    });
                }else if (numberOfSeats.intValue() == 2){
                    wolne.forEach(e ->
                    {
                        if (e.getNumer() + 1 != brakujace.get(0).getNumer() && e.getNumer() + 1 != brakujace.get(1).getNumer()) {
                            wolneNumer.add(e.getNumer());
                        }
                    });
                    return wolne.stream().filter(e -> wolneNumer.contains(e.getNumer())).limit(2).toList();
                }
                //wolneNumer.forEach(System.out::println);

                return wolne.stream().filter(e -> wolneNumer.contains(e.getNumer())).toList();
            }else {
                return getWolneMiejsca()
                        .stream()
                        .filter(e -> Objects.equals(e.getRzad(), rzad))
                        .limit(numberOfSeats.intValue())
                        .collect(Collectors.toList());
            }
        }else{
            return getWolneMiejsca()
                    .stream()
                    .filter(e -> Objects.equals(e.getRzad(), rzad))
                    .limit(numberOfSeats.intValue())
                    .collect(Collectors.toList());
        }
    }


}
