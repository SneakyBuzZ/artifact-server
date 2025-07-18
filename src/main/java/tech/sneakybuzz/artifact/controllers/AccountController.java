package tech.sneakybuzz.artifact.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sneakybuzz.artifact.services.AccountService;
import tech.sneakybuzz.artifact.utils.ApiResponse;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
  private final AccountService accountService;

  @GetMapping("/send-verification-email")
  public ResponseEntity<ApiResponse<Object>> sendVerificationEmail(Principal principal) {
    accountService.sendVerificationEmail(principal.getName());
    return ResponseEntity.ok(ApiResponse.success(
        200,
        null,
        "Verification email sent successfully."
    ));
  }

  @PostMapping("/verify")
  public ResponseEntity<ApiResponse<Void>> verify(@RequestParam String token){

    return ResponseEntity.ok(ApiResponse.success(
        200,
        null,
        "Account verification endpoint hit successfully." + jwtToken
    ));
  }

}
