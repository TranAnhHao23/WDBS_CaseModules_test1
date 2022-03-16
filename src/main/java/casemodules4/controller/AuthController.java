package casemodules4.controller;

import casemodules4.model.Account;
import casemodules4.model.Role;
import casemodules4.model.User;
import casemodules4.security.jwt.JwtProvider;
import casemodules4.security.jwt.response.JwtResponse;
import casemodules4.security.jwt.response.ResponseMessage;
import casemodules4.security.service.UserPrinciple;
import casemodules4.service.IAccountService;
import casemodules4.service.IRoleService;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account currentUser = accountService.findByUsername(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), token, userDetails.getUsername(), userDetails.getAuthorities(), new ResponseMessage("Login Success!")));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return new ResponseEntity<>("Hello User", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Account account) {
        if (accountService.existsByEmail(account.getEmail()) | accountService.existsByUsername(account.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Email or Username existed!"),HttpStatus.OK);
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.save(account);
        return new ResponseEntity<>(new ResponseMessage("Login Success!"),HttpStatus.CREATED);
    }

}
