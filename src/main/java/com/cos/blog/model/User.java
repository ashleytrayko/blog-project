package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가  mySql에 테이블이 생성
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //프로젝트에 연결된 DB넘버링 전략을 따라
	private int id;
	
	@Column(nullable=false, length=30, unique = true)
	private String username;
	
	@Column(nullable=false, length=100)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role; // ENUM을 쓰는게 좋다.
	
	@CreationTimestamp //시간이 자동입력 
	private Timestamp createDate;

	
}
