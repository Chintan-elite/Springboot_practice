package com.blog.app.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	private String name;
	@Email
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;
}
