package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO
//자동으로 bean등록이 된다
//Repository 생략가능 
public interface UserRepository extends JpaRepository<User, Integer> {
	// SELECT * FROM USER WHERE USERNAME = 1?;
	Optional<User> findByUsername(String username);
	
	
	//Jpa Naming 쿼리
	//SELECT * FROM USER WHERE UESRNAME = ?1 AND PASSWORD = ?2;
	//User findByUsernameAndPassword(String username, String password);
	
//	@Query(value="SELECT * FROM USER WHERE UESRNAME = ?1 AND PASSWORD = ?2;", nativeQuery=true)
//	User login(String username, String password);
}
