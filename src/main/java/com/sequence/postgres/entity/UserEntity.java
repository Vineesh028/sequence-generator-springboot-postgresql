package com.sequence.postgres.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sequence.postgres.util.UserNameSequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

	private long id;

	@Column(nullable = false)
	private String name;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "username_seq")
	@GenericGenerator(name = "username_seq",
					  strategy = "com.sequence.postgres.util.UserNameSequenceGenerator", parameters = {
			@Parameter(name = UserNameSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = UserNameSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "User"),
			@Parameter(name = UserNameSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	@Column(name = "user_name")
	private String userName;

	@Column(nullable = false, unique = true)
	private String email;

}