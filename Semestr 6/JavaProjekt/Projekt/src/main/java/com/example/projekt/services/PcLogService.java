package com.example.projekt.services;

import com.example.projekt.models.LogSummary;
import com.example.projekt.models.PcLog;

import java.util.List;

public interface PcLogService {
    public List<PcLog> getByPcId(int id);
    public List<LogSummary> getRaportByPc(int id);
}
