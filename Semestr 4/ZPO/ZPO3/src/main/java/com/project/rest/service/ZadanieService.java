package com.project.rest.service;

import com.project.rest.model.Zadanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ZadanieService {

    Optional<Zadanie> getZadanie(Integer zadanieId);
    Zadanie setZadanie(Zadanie zadanie);
    void deleteZadanie(Integer zadanieId);
    Page<Zadanie> getZadanie(Pageable pageable);
    Page<Zadanie> searchByNazwa(Integer projektId, Pageable pageable);
}
