package tech.sneakybuzz.artifact.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
  @NotNull(message = "First name should not be empty")
  @Size(min = 2, message = "First name must be more than 2")
  private String firstName;

  @NotNull(message = "Last name should not be empty")
  @Size(min = 2, message = "Last name must be more than 2")
  private String lastName;

  @NotNull(message = "Email should not be empty")
  @Email(message = "Enter valid email")
  private String email;

  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;
}
