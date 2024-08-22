package com.nagarro;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodOrderingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderingAppApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
//	@Autowired
//	private RoleRepository roleRepository;
//	
//	private ModelMapper mapper;
//	private PasswordEncoder encoder;
//	public void run(String... args) throws Exception{
//		
//	    CustomerRequest customerDto=new CustomerRequest();
//	    customerDto.setUsername("aryan");
//	    customerDto.setEmail("aryan@gmail.com");
//	    customerDto.setAddress("Varanasi");
//	    customerDto.setPassword(this.encoder.encode("aryan"));
//	    Customer customer = this.mapper.map(customerDto, Customer.class);
//	    
//	    Role role1=new Role();
//	    role1.setRoleid(101);
//	    role1.setRoleName("ADMIN");
//	    
//	    Role role2=new Role();
//	    role2.setRoleid(102);
//	    role2.setRoleName("USER");
//	    
//	    customer.setRoles(role1,role2);
//	    
////	    this.roleRepository.save(role1);
//	    
//	    Role role2=new Role();
//	    role1.setRoleid(102);
//	    role1.setRoleName("USER");
//	    
//	    this.roleRepository.save(role2);
//	    
//    }

}
