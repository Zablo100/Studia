package com.example.projekt.services;

import com.example.projekt.models.LogSummary;
import com.example.projekt.models.PcLog;
import com.example.projekt.repo.PcLogRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class PcLogServiceImpl implements PcLogService {
    private final PcLogRepo repo;

    @Override
    public List<PcLog> getByPcId(int id) {
        return repo.findAll().stream().filter(pc -> pc.getPcId() == id).toList();
    }

    @Override
    public List<LogSummary> getRaportByPc(int id) {
        List<PcLog> data = repo.findAll().stream().filter(pc -> pc.getPcId() == id).toList();
        List<LogSummary> logs = new ArrayList<>();

        Map<Month, Integer> xx = new HashMap<>();

        data.forEach(element -> {
            xx.merge(element.getDate().getMonth(), 1, Integer::sum);
        });

        for(Map.Entry<Month, Integer> entry : xx.entrySet() ){
            LogSummary log = new LogSummary();
            log.setMonth(getMonthPLName(entry.getKey()));
            log.setCount(entry.getValue());
            logs.add(log);
        }

        return logs;

    }

    private String getMonthPLName(Month month){
        switch (month.getValue()){
            case 1:
                return "Styczeń";
            case 2:
                return "Luty";

            case 3:
                return "Marzec";

            case 4:
                return "Kwiecień";
            case 5:
                return "Maj";
            case 6:
                return "Czerwiec";
            case 7:
                return "Lipiec";
            case 8:
                return "Sierpień";
            case 9:
                return "Wrzesień";
            case 10:
                return "Październik";
            case 11:
                return "Listopad";
            case 12:
                return "Grudzień";
        }

        return null;
    }


}
