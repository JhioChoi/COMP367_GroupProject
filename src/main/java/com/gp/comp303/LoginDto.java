package com.gp.comp303;

public class LoginDto {
    @NotEmpty(message = "Username is required.")
    private String userName;

    @NotEmpty(message = "Password is required")
    private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    
}

