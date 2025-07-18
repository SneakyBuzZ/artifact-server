package tech.sneakybuzz.artifact.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tech.sneakybuzz.artifact.model.enums.OnBoardingStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "uuid", updatable = false)
  private UUID id;

  @Column(name = "first_name", nullable = false,length = 100)
  private String firstName;

  @Column(name = "last_name", nullable = false,length = 100)
  private String lastName;

  @Email
  @NotBlank
  @Column(unique = true,nullable = false,length = 255)
  private String email;

  @Column(name = "profile_url")
  private String profileUrl;

  @Enumerated(EnumType.STRING)
  @Column(name = "on_boarding_status", nullable = false , columnDefinition = "varchar(100) default 'REGISTERED_NOT_VERIFIED'")
  private OnBoardingStatus onBoardingStatus;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<AccountEntity> accounts = new ArrayList<>();

  @CreationTimestamp
  @Column(updatable = false)
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;
}