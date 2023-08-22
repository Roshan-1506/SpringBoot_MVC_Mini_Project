package com.cdac.springbootdatabasesecuritydemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.cdac.springbootdatabasesecuritydemo.pojo.User;

public interface UserRepository extends CrudRepository<User, Integer>
{
	@Query(value = "FROM User u WHERE u.username = :uname")
	User getUserByUserName(@Param("uname") String username);
}
