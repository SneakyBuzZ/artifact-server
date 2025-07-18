package tech.sneakybuzz.artifact.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
  @NotNull(message = "Email should not be empty")
  @Email(message = "Enter valid email")
  private String email;

  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;
}
