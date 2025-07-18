package tech.sneakybuzz.artifact.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.sneakybuzz.artifact.dto.requests.LoginRequest;
import tech.sneakybuzz.artifact.services.AuthServices;
import tech.sneakybuzz.artifact.utils.ApiResponse;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthServices authServices;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<Void>> login(@Valid @RequestBody LoginRequest request){
    try{
      String jwtToken = authServices.login(request);

      ResponseCookie cookie = ResponseCookie
          .from("jwt", jwtToken)
          .httpOnly(true)
          .sameSite("Strict")
          .path("/")
          .maxAge(Duration.ofDays(1))
          .build();
      ApiResponse<Void> response = ApiResponse.<Void>builder()
          .status(HttpStatus.OK.value())
          .success(true)
          .message("User logged in successfully.")
          .build();

      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
    }catch(Exception e){
      return ResponseEntity.badRequest().body(
          ApiResponse
              .<Void>builder()
              .status(400)
              .data(null)
              .message("Invalid credentials provided.")
              .build()
      );
    }
  }
}
