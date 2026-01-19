package com.ecom.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecom.user.controller.UsersController;
import com.ecom.user.entity.Roles;
import com.ecom.user.entity.Users;
import com.ecom.user.repository.RolesRepository;
import com.ecom.user.repository.UsersRepository;
import com.ecom.user.request.UsersRequest;
import com.ecom.user.response.UsersDetails;

import jakarta.transaction.Transactional;

@Service
public class UsersServiceImplementation implements UsersService
{

    private final UsersController usersController;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	RolesRepository rolesRepository;

    UsersServiceImplementation(UsersController usersController) {
        this.usersController = usersController;
    }
	
	@Override
	@Transactional
	public long createUser(UsersRequest request) {
		
		Users users = new Users();
		users.setFirstName(request.getFirstName());
		users.setLastName(request.getLastName());
		users.setEmail(request.getEmail());
		users.setMobile(request.getMobile());
		users.setPassword(request.getPassword());
		
		users = usersRepository.save(users);
		
		Roles roles = new Roles();
		roles.setUserId(users.getUserId());
		roles.setRole(request.getRole());
		
		rolesRepository.save(roles);
		
		return users.getUserId();
	}

	@Override
	public UsersDetails getUserDetails(long userId) {
		Users users = usersRepository.findById(userId).get();
		
		if(users==null) {
			//exception
		}
		Roles roles = rolesRepository.findByUserId(users.getUserId());
		if(roles==null) {
			//exception
		}
		UsersDetails usersDetails = new UsersDetails();
		usersDetails.setFirstName(users.getFirstName());
		usersDetails.setLastName(users.getLastName());
		usersDetails.setEmail(users.getEmail());
		usersDetails.setMobile(users.getEmail());
		usersDetails.setRole(roles.getRole());
		
		return usersDetails;
	}

}
