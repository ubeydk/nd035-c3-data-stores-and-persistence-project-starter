package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = scheduleService.createSchedule(
                new ScheduleEntity(scheduleDTO),scheduleDTO.getPetIds(), scheduleDTO.getEmployeeIds());
        return new ScheduleDTO(scheduleEntity);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleEntity> scheduleEntities = scheduleService.getAllSchedules();
        return scheduleEntities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleEntity> scheduleEntities = scheduleService.getScheduleForPet(petId);
        return scheduleEntities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleEntity> scheduleEntities = scheduleService.getScheduleForEmployee(employeeId);
        return scheduleEntities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleEntity> scheduleEntities = scheduleService.getScheduleForCustomer(customerId);
        return scheduleEntities.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }
}
