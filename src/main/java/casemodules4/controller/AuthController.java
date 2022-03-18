package casemodules4.controller;

import casemodules4.model.Account;
import casemodules4.model.Role;
import casemodules4.model.User;
import casemodules4.security.jwt.JwtProvider;
import casemodules4.security.jwt.request.SignUp;
import casemodules4.security.jwt.response.JwtResponse;
import casemodules4.security.jwt.response.ResponseMessage;
import casemodules4.service.IAccountService;
import casemodules4.service.IRoleService;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public JavaMailSender mailSender;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private IAccountService accountService;
    @Autowired
    IUserService userService;
    @Autowired
    IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getAccount(), account.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account currentUser = accountService.findByUsername(account.getAccount()).get();
        if (accountService.checkLogin(currentUser)){
            return ResponseEntity.ok(new JwtResponse(currentUser.getId(), token, userDetails.getUsername(), userDetails.getAuthorities(), new ResponseMessage("Login Success!")));
        } else {
            return new ResponseEntity<>(new ResponseMessage("account not found or wrong information"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return new ResponseEntity<>("Hello User", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUp signUp) {
        if (accountService.existsByEmail(signUp.getEmail()) || accountService.existsByUsername(signUp.getAccount())){
            return new ResponseEntity<>(new ResponseMessage("Email or Username existed!"),HttpStatus.BAD_REQUEST);
        }
        Account account = new Account(signUp.getAccount(), passwordEncoder.encode(signUp.getPassword()), signUp.getEmail());
        Set<String> strRoles = signUp.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach( role -> {
            switch (role){
                case "admin":
                    roles.add(roleService.findByRoleName("ROLE_ADMIN").orElseThrow( () -> new RuntimeException("Role not found")));
                    break;
                default:
                    roles.add(roleService.findByRoleName("ROLE_USER").orElseThrow( () -> new RuntimeException("Role not found")));
            }
        });
        account.setRoles(roles);
        accountService.save(account);
        User user = new User(signUp.getFullName(), signUp.getPhoneNumber(), signUp.getAddress(), account);
        userService.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUp.getEmail());
        message.setSubject("Sign up account confirm email");
        message.setText("Hello, Im testing Simple Email! Sign up success with account: " + signUp.getAccount());
        this.mailSender.send(message);
        return new ResponseEntity<>(new ResponseMessage("Sign up Success! Email sent!"),HttpStatus.CREATED);
    }

}
