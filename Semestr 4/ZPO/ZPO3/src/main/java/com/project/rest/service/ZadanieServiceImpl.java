package com.project.rest.service;

import com.project.rest.model.Zadanie;
import com.project.rest.repository.ProjektRepository;
import com.project.rest.repository.StudentRepository;
import com.project.rest.repository.ZadanieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ZadanieServiceImpl implements ZadanieService{

    private ProjektRepository projektRepository;
    private ZadanieRepository zadanieRepository;

    public ZadanieServiceImpl(ProjektRepository projektRepository, ZadanieRepository zadanieRepository){
        this.projektRepository = projektRepository;
        this.zadanieRepository = zadanieRepository;
    }


    @Override
    public Optional<Zadanie> getZadanie(Integer zadanieId) {
        return zadanieRepository.findById(zadanieId);
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        zadanieRepository.save(zadanie);
        return zadanie;
    }

    @Override
    @Transactional
    public void deleteZadanie(Integer zadanieId) {
        zadanieRepository.deleteById(zadanieId);
    }

    @Override
    public Page<Zadanie> getZadanie(Pageable pageable) {
        return zadanieRepository.findAll(pageable);
    }

    @Override
    public Page<Zadanie> searchByNazwa(Integer projektId, Pageable pageable) {
        return zadanieRepository.findZadaniaProjektu(projektId, pageable);
    }
}
