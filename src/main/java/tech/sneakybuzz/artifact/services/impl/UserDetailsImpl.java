package tech.sneakybuzz.artifact.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.sneakybuzz.artifact.model.entities.AccountEntity;
import tech.sneakybuzz.artifact.model.entities.UserEntity;
import tech.sneakybuzz.artifact.repositories.AccountRepository;
import tech.sneakybuzz.artifact.repositories.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {
  private final UserRepository userRepository;
  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String email) {
    try{
      UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
      AccountEntity account = accountRepository.findByUser(user);
      return new User(user.getEmail(),account.getPassword(),new ArrayList<>());
    }catch (UsernameNotFoundException e) {
      throw new UsernameNotFoundException("User not found with email: " + email);
    } catch (Exception e) {
      throw new RuntimeException("An error occurred while loading user details", e);
    }
  }
}
