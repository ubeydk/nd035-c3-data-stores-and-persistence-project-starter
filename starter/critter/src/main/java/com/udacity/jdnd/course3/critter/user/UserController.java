package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer") //
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerEntity customerEntity = customerService.saveCustomer(new CustomerEntity(customerDTO));
        return new CustomerDTO(customerEntity);
    }

    @GetMapping("/customer") //
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerEntity> customerEntities = customerService.getAllCustomers();
        return customerEntities.stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        CustomerEntity customerEntity = customerService.getOwnerByPet(petId);
        if(customerEntity != null)
            return new CustomerDTO(customerEntity);
        return null;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeService.saveEmployee(new EmployeeEntity(employeeDTO));
        return new EmployeeDTO(employeeEntity);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        EmployeeEntity employeeEntity = employeeService.getEmployee(employeeId);
        if(employeeEntity != null)
            return new EmployeeDTO(employeeEntity);
        return null;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        List<EmployeeEntity> employeeEntities =
                employeeService.findEmployeesForService(employeeRequestDTO.getSkills(), employeeRequestDTO.getDate());
        return employeeEntities.stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

}
