package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public PetDTO savePet(PetDTO petDTO) {
        PetEntity petEntity = new PetEntity(petDTO);
        if(petDTO.getOwnerId() > 0)
            petEntity.setOwner(customerRepository.getOne(petDTO.getOwnerId()));
        PetEntity savedPetEntity = petRepository.save(petEntity);
        CustomerEntity owner = savedPetEntity.getOwner();
        if(owner != null){
            owner.getPets().add(savedPetEntity);
            customerRepository.save(owner);
        }
        return new PetDTO(savedPetEntity);
    }

    public PetDTO getPet(long petId) {
        PetEntity petEntity = getPetEntityById(petId);
        if(petEntity != null)
            return new PetDTO(petEntity);
        return null;
    }

    public List<PetDTO> getPets(){
        List<PetEntity> petEntites = petRepository.findAll();
        return petEntites.stream().map(PetDTO::new).collect(Collectors.toList());
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {

        Optional<CustomerEntity> optionalOwnerEntity = customerRepository.findById(ownerId);
        if(optionalOwnerEntity.isPresent())
            return optionalOwnerEntity.get().getPets().stream().map(PetDTO::new).collect(Collectors.toList());
        return new ArrayList<PetDTO>();
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
