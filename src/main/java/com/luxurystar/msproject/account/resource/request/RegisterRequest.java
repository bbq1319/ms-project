package com.luxurystar.msproject.account.resource.request;

import com.luxurystar.msproject.account.AccountRole;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

	@Email
	@NotEmpty
	@Size(max = 300)
	private String email;
	@NotEmpty
	@Size(max = 60)
	private String password;
	@NotEmpty
	@Size(max = 60)
	private String confirmPassword;
	@NotEmpty
	@Size(max = 30)
	private String name;
	@NotNull
	private AccountRole role;

	@AssertTrue(message = "비밀번호가 서로 일치하지 않습니다.")
	public boolean isPassword() {
		return password.equals(confirmPassword);
	}
}
