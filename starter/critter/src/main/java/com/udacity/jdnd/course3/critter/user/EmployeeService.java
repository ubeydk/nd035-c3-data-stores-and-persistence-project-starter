package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity saveEmployee = employeeRepository.save(new EmployeeEntity(employeeDTO));
        return new EmployeeDTO(saveEmployee);
    }

    public EmployeeDTO getEmployee(long employeeId){
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);
        if(!optionalEmployeeEntity.isPresent())
            return null;
        return new EmployeeDTO(optionalEmployeeEntity.get());
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {

        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);
        if(optionalEmployeeEntity.isPresent()){
            EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
            employeeEntity.setDaysAvailable(daysAvailable);
            employeeRepository.save(employeeEntity);
        }
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

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        List<EmployeeEntity> employeeEntities = employeeRepository.getAllByDaysAvailableContains(employeeDTO.getDate().getDayOfWeek());
        List<EmployeeEntity> filteredEmployeeEntities = employeeEntities.stream().filter(employeeEntity -> employeeEntity.getSkills().containsAll(employeeDTO.getSkills())).collect(Collectors.toList());
        return filteredEmployeeEntities.stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }
}
