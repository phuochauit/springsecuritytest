package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.enums.RoleName;
import com.example.demo.infrastructure.IRoleDao;
import com.example.demo.infrastructure.IUserDao;
import com.example.demo.services.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run (IUserService iUserService , IRoleDao iRoleDao , IUserDao iUserRepository , PasswordEncoder passwordEncoder)
	{return  args ->
	{   iUserService.saveRole(new Role(RoleName.USER));
		iUserService.saveRole(new Role(RoleName.ADMIN));
		iUserService.saveUser(new User("admin", passwordEncoder.encode("admin"), new ArrayList<>()));
		iUserService.saveUser(new User("user", passwordEncoder.encode("user"), new ArrayList<>()));
		iUserService.saveUser(new User("nphau1", passwordEncoder.encode("nphau1"), new ArrayList<>()));

		Role role = iRoleDao.findByRoleName(RoleName.ADMIN);
		User user = iUserRepository.findByUsername("admin").orElse(null);
		user.getRoles().add(role);
		iUserService.saveUser(user);

		User userr = iUserRepository.findByUsername("user").orElse(null);
		Role rolee = iRoleDao.findByRoleName(RoleName.USER);
		userr.getRoles().add(rolee);
		iUserService.saveUser(userr);

		User userrr = iUserRepository.findByUsername("nphau1").orElse(null);
		Role roleee1 = iRoleDao.findByRoleName(RoleName.ADMIN);
		Role roleee2 = iRoleDao.findByRoleName(RoleName.USER);
		userr.getRoles().add(roleee1);
		userr.getRoles().add(roleee2);
		iUserService.saveUser(userrr);

	};}

}
