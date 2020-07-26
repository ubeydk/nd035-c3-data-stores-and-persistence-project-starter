package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.CustomerEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pets")
public class PetEntity {
    @Id
    @GeneratedValue
    @Column(name = "pet_id")
    private long id;
    @Enumerated(EnumType.STRING)
    private PetType type;
    @Column(name = "pet_name")
    private String name;
    @ManyToOne
    private CustomerEntity owner;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "notes")
    private String notes;

    public PetEntity(){}

    protected PetEntity(PetDTO petDTO, CustomerEntity owner){
        this.id = petDTO.getId();
        this.type = petDTO.getType();
        this.name = petDTO.getName();
        this.owner = owner;
        this.birthDate = petDTO.getBirthDate();
        this.notes = petDTO.getNotes();
    }

    protected PetEntity(PetDTO petDTO){
        this.id = petDTO.getId();
        this.type = petDTO.getType();
        this.name = petDTO.getName();
        this.birthDate = petDTO.getBirthDate();
        this.notes = petDTO.getNotes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerEntity getOwner() {
        return owner;
    }

    public void setOwner(CustomerEntity owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
