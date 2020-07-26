package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public CustomerEntity saveCustomer(CustomerEntity customerEntity){
        return customerRepository.save(customerEntity);
    }

    public List<CustomerEntity> getAllCustomers(){
        return customerRepository.findAll();
    }

    public CustomerEntity getOwnerByPet(long petId){
        Optional<PetEntity> optionalPetEntity = petRepository.findById(petId);
        if(optionalPetEntity.isPresent())
            return optionalPetEntity.get().getOwner();
        return null;
    }

    public CustomerEntity getCustomerEntityById(long customer_id){
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customer_id);
        if(optionalCustomerEntity.isPresent())
            return optionalCustomerEntity.get();
        return null;
    }
}
