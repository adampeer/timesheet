package com.pm.app.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.app.dto.LoginResponseDTO;
import com.pm.app.repository.RoleRepository;

@Service
@Transactional
public class AuthenticationService {

  @SuppressWarnings("unused")
  private RoleRepository roleRepository;

  @SuppressWarnings("unused")
  private PasswordEncoder passwordEncoder;

  private AuthenticationManager authenticationManager;

  private TokenService tokenService;

  public AuthenticationService(RoleRepository roleRepository,
      PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  public ResponseEntity<LoginResponseDTO> loginUser(String username, String password) {

    try {
      Authentication auth = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, password));

      String token = tokenService.generateJwt(auth);

      return ResponseEntity.ok(new LoginResponseDTO(token));

    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

}
