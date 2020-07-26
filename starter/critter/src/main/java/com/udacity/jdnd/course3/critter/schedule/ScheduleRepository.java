package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> getAllByPetsContains(PetEntity petEntity);

    List<ScheduleEntity> getAllByPetsIn(List<PetEntity> petEntities);

    List<ScheduleEntity> getAllByEmployeesContains(EmployeeEntity employeeEntity);



}
