package org.pm.authservice.dto;

// represents response data transfer object
public class LoginResponseDTO {
  private final String token;

  public LoginResponseDTO(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
