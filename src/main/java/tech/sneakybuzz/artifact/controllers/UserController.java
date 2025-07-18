package tech.sneakybuzz.artifact.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.sneakybuzz.artifact.dto.requests.RegisterRequest;
import tech.sneakybuzz.artifact.services.UserService;
import tech.sneakybuzz.artifact.utils.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest request){
    userService.registerUser(request);
    return ResponseEntity.ok().body(ApiResponse.success(
        HttpStatus.CREATED.value(),
        null,
        "User registered successfully."
    ));
  }
}
