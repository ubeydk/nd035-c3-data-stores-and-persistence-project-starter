package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetEntity petEntity = new PetEntity(petDTO);
        PetEntity savedPetEntity =  petService.savePet(petEntity, petDTO.getOwnerId());
        return new PetDTO(savedPetEntity);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        PetEntity petEntity = petService.getPet(petId);
        if(petEntity != null)
            return new PetDTO(petEntity);
        return null;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetEntity> petEntities = petService.getPets();
        return petEntities.stream().map(PetDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetEntity> petEntities = petService.getPetsByOwner(ownerId);
        return petEntities.stream().map(PetDTO::new).collect(Collectors.toList());
    }
}
