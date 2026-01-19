package com.ecom.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecom.user.entity.Roles;

@Repository
public interface RolesRepository extends CrudRepository<Roles, Long>
{

	Roles findByUserId(long userId);

}
