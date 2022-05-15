package alm.carrentalproject.Service;

import alm.carrentalproject.Entity.User;
import alm.carrentalproject.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final static String PW_ERROR = "Password must be at least 6 " +
            "characters long and must contain at least one uppercase letter, one lower case letter, and one number. It must not be longer than 100 characters.";

    @Transactional
    public String register(@Valid User user, BindingResult result) {
        if (!user.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,100}$")) {
            FieldError pwErr = new FieldError("user", "password", PW_ERROR);
            result.addError(pwErr);
        }
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if (userExists) {
            FieldError nameTaken = new FieldError("user", "username", "Username already exist!");
            result.addError(nameTaken);
        }
        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (emailExists) {
            FieldError emailExisted = new FieldError("user", "email", "Email already exist!");
            result.addError(emailExisted);
        }
        if (!user.getPassword().equals(user.getPasswordRepeat())) {
            FieldError pwRepeat = new FieldError("user", "passwordRepeat", "Please enter the same password");
            result.addError(pwRepeat);
        }
        if (result.hasErrors()) {
            return "register";
        }
        String encodePW = bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println(encodePW);
        user.setPassword(encodePW);
        userRepository.save(user);
        return "login";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
