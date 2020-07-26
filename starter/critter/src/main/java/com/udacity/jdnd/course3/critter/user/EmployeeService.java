package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity getEmployee(long employeeId){
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);
        if(optionalEmployeeEntity.isPresent())
            return optionalEmployeeEntity.get();
        return null;
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);
        if(optionalEmployeeEntity.isPresent()){
            EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
            employeeEntity.setDaysAvailable(daysAvailable);
            employeeRepository.save(employeeEntity);
        }
    }

    public List<EmployeeEntity> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        List<EmployeeEntity> employeeEntities = employeeRepository.getAllByDaysAvailableContains(date.getDayOfWeek());
        List<EmployeeEntity> filteredEmployeeEntities = employeeEntities.stream().filter(employeeEntity -> employeeEntity.getSkills().containsAll(skills)).collect(Collectors.toList());
        return filteredEmployeeEntities;
    }

    public List<EmployeeEntity> getEmployeeEntitiesById(List<Long> employeeIds){
        return employeeRepository.findAllById(employeeIds);
    }

    public EmployeeEntity getEmployeeEntityById(long employeeId){
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);
        if(optionalEmployeeEntity.isPresent())
            return optionalEmployeeEntity.get();
        return null;
    }


}
