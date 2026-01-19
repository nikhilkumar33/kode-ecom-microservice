package com.ecom.user.service;

import com.ecom.user.request.UsersRequest;
import com.ecom.user.response.UsersDetails;

public interface UsersService {
	
	public long createUser(UsersRequest request);

	public UsersDetails getUserDetails(long userId);

}
