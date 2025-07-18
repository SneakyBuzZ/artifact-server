package tech.sneakybuzz.artifact.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.sneakybuzz.artifact.dto.requests.RegisterRequest;
import tech.sneakybuzz.artifact.model.entities.AccountEntity;
import tech.sneakybuzz.artifact.model.entities.UserEntity;
import tech.sneakybuzz.artifact.model.enums.AccountProvider;
import tech.sneakybuzz.artifact.model.enums.OnBoardingStatus;
import tech.sneakybuzz.artifact.repositories.AccountRepository;
import tech.sneakybuzz.artifact.repositories.UserRepository;
import tech.sneakybuzz.artifact.services.UserService;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void registerUser(RegisterRequest request){
    if(userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("Email already exists");
    }

    UserEntity newUser = UserEntity
        .builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .onBoardingStatus(OnBoardingStatus.REGISTERED_NOT_VERIFIED)
        .build();
    userRepository.save(newUser);

    AccountEntity newAccount = AccountEntity
        .builder()
        .user(newUser)
        .provider(AccountProvider.EMAIL)
        .providerAccountId(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .isVerified(false)
        .build();
    accountRepository.save(newAccount);
  }

//  @Override
//  public UserDetails loadUserByUsername(String email) {
//    try{
//      UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
//      AccountEntity account = accountRepository.findByUser(user);
//      return new User(user.getEmail(),account.getPassword(),new ArrayList<>());
//    }catch (UsernameNotFoundException e) {
//      throw new UsernameNotFoundException("User not found with email: " + email);
//    } catch (Exception e) {
//      throw new RuntimeException("An error occurred while loading user details", e);
//    }
//  }
}
