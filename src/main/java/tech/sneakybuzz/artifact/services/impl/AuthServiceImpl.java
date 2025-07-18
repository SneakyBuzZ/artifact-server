package tech.sneakybuzz.artifact.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.sneakybuzz.artifact.dto.requests.LoginRequest;
import tech.sneakybuzz.artifact.services.AppUserDetailsService;
import tech.sneakybuzz.artifact.services.AuthServices;
import tech.sneakybuzz.artifact.utils.jwt.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthServices {
  private final AuthenticationManager authenticationManager;
  private final AppUserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;

  @Override
  public String login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
    return jwtUtil.generateToken(userDetails);
  }
}
