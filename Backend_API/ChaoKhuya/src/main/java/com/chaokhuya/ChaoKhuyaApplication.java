package com.chaokhuya;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chaokhuya.config.WebConstants;
import com.chaokhuya.model.Role;
import com.chaokhuya.repository.IRoleRepository;

@SpringBootApplication
public class ChaoKhuyaApplication implements CommandLineRunner{
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Bean
	public ModelMapper modelMapper() 
	{
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		
		SpringApplication.run(ChaoKhuyaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		try {
			
			Role role = new Role();
			role.setId(WebConstants.ADMIN);
			role.setName("ROLE_ADMIN");
			roleRepository.save(role);
			
			Role role1 = new Role();
			role1.setId(WebConstants.USER);
			role1.setName("ROLE_USER");
			roleRepository.save(role1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
