package com.example.projekt.services;

import com.example.projekt.models.ApiModels.ComputersResponse;
import com.example.projekt.models.Computer;
import com.example.projekt.models.Employee;
import com.example.projekt.repo.ComputerRepo;
import com.example.projekt.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService{
    private final ComputerRepo computerRepo;
    private final EmployeeRepo employeeRepo;

    @Override
    public List<ComputersResponse> getAllComputers() {
        List<Computer> computers = computerRepo.findAll().stream().toList();
        List<ComputersResponse> response = new ArrayList<>();

        computers.forEach(pc ->{
            if (pc.getEmployeeId() != null){
                Employee employee = employeeRepo.findById(pc.getEmployeeId()).get();
                response.add(new ComputersResponse(pc, employee.getName() + " " + employee.getLastName()));
            }else{
                response.add(new ComputersResponse(pc, "Brak"));
            }
        });

        return response;
    }

    @Override
    public Computer getById(int id) {
        return computerRepo.findById(id).get();
    }

}
