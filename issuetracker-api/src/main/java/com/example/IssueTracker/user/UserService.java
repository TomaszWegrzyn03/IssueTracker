package com.example.IssueTracker.user;
import com.example.IssueTracker.user.registration.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(RegistrationRequest registrationRequest) {
        String password = passwordEncoder.encode(registrationRequest.getPassword());
        User user = new User(
                registrationRequest.getUsername(),
                registrationRequest.getEmail(),UserRole.ROLE_USER,
                password,
                LocalDate.now(),
                true,
                false);
        userRepository.save(user);
        return "Registration complete";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<SimpleUserDto> getAllSimpleUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new SimpleUserDto(
                        user.getUserId(),
                        user.getUsername()))
                .toList();
    }

    public List<User> getUsersByProject(Long projectId) {
        return userRepository.findUsersByProjectId(projectId);
    }
}
