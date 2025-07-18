package tech.sneakybuzz.artifact.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyAccountRequest {
  @NotNull(message = "Email should not be empty")
  @Email(message = "Enter valid email")
  private String email;
}
