package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO){
        CustomerEntity savedCustomerEntity = customerRepository.save(new CustomerEntity(customerDTO));
        return new CustomerDTO(savedCustomerEntity);
    }

    public List<CustomerDTO> getAllCustomers(){
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(long petId){
        Optional<PetEntity> optionalPetEntity = petRepository.findById(petId);
        if(optionalPetEntity.isPresent()){
            return new CustomerDTO(optionalPetEntity.get().getOwner());
        }
        return null;
    }

    public CustomerEntity getCustomerEntityById(long customer_id){
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customer_id);
        if(optionalCustomerEntity.isPresent())
            return optionalCustomerEntity.get();
        return null;
    }
}
