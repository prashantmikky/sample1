package com.sample1.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample1.app.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
}
