package com.nancy.Airline.dao;

import com.nancy.Airline.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
