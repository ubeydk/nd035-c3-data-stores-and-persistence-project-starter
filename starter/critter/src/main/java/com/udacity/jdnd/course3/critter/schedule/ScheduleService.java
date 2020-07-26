package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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

    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity, List<Long> pet_ids, List<Long> employee_ids) {
        if(pet_ids != null)
            scheduleEntity.setPets(petRepository.findAllById(pet_ids));
        if(employee_ids != null)
            scheduleEntity.setEmployees(employeeRepository.findAllById(employee_ids));
        return scheduleRepository.save(scheduleEntity);
    }

    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> getScheduleForPet(long petId) {
        return scheduleRepository.getAllByPetsContains(petRepository.getOne(petId));
    }

    public List<ScheduleEntity> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.getAllByEmployeesContains(employeeRepository.getOne(employeeId));
    }

    public List<ScheduleEntity> getScheduleForCustomer(long customerId) {
        CustomerEntity customerEntity = customerRepository.getOne(customerId);
        return scheduleRepository.getAllByPetsIn(customerEntity.getPets());
    }
}
