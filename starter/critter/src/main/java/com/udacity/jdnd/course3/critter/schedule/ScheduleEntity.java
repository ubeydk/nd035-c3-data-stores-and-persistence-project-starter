package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "schedules")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    private long id;
    @ManyToMany
    private List<EmployeeEntity> employees;
    @ManyToMany
    private List<PetEntity> pets;
    private LocalDate date;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<EmployeeSkill> activities;

    public ScheduleEntity(){
        this.pets = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public ScheduleEntity(ScheduleDTO scheduleDTO){
        this();
        this.id = scheduleDTO.getId();
        this.date = scheduleDTO.getDate();
        this.activities = scheduleDTO.getActivities();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employeeIds) {
        this.employees = employeeIds;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
