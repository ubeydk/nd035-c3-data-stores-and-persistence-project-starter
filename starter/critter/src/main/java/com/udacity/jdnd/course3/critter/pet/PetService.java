package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public PetEntity savePet(PetEntity petEntity, long ownerId) {
        if(ownerId > 0)
            petEntity.setOwner(customerRepository.getOne(ownerId));
        PetEntity savedPetEntity = petRepository.save(petEntity);
        CustomerEntity owner = savedPetEntity.getOwner();
        if(owner != null){
            owner.getPets().add(savedPetEntity);
            customerRepository.save(owner);
        }
        return savedPetEntity;
    }

    public PetEntity getPet(long petId) {
        return getPetEntityById(petId);
    }

    public List<PetEntity> getPets(){
        return petRepository.findAll();
    }

    public List<PetEntity> getPetsByOwner(long ownerId) {

        Optional<CustomerEntity> optionalOwnerEntity = customerRepository.findById(ownerId);
        if(optionalOwnerEntity.isPresent())
            return optionalOwnerEntity.get().getPets();
        return new ArrayList<PetEntity>();
    }

    public List<PetEntity> getPetEntitiesById(List<Long> pet_ids){
        return petRepository.findAllById(pet_ids);
    }
    public PetEntity getPetEntityById(Long pet_id){
        Optional<PetEntity> optionalPetEntity = petRepository.findById(pet_id);
        if(optionalPetEntity.isPresent())
            return optionalPetEntity.get();
        return null;
    }
}
