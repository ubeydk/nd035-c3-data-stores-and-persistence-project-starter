package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity(scheduleDTO);
        if(scheduleDTO.getEmployeeIds() != null && !scheduleDTO.getEmployeeIds().isEmpty())
            scheduleEntity.setEmployees(employeeRepository.findAllById(scheduleDTO.getEmployeeIds()));
        if(scheduleDTO.getPetIds() != null && !scheduleDTO.getPetIds().isEmpty()){
            scheduleEntity.setPets(petRepository.findAllById(scheduleDTO.getPetIds()));
        }
        ScheduleEntity savedScheduleEntity = scheduleRepository.save(scheduleEntity);
        return new ScheduleDTO(savedScheduleEntity);
    }

    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findAll();
        return scheduleEntities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.getAllByPetsContains(petRepository.getOne(petId));
        return scheduleEntities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.getAllByEmployeesContains(employeeRepository.getOne(employeeId));
        return scheduleEntities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        CustomerEntity customerEntity = customerRepository.getOne(customerId);
        List<ScheduleEntity> scheduleEntities = scheduleRepository.getAllByPetsIn(customerEntity.getPets());
        return scheduleEntities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }
}
