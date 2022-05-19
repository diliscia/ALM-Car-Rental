package alm.carrentalproject.Entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    public enum Role {
        CLIENT,
        ADMIN
    }

    @Column(nullable = false)
    @Pattern(regexp = "[a-z0-9]{4,20}", message = "Username can only contains lowercase letters and numbers. And must be between 4-20 characters.")
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordRepeat;

    @NotEmpty(message = "First name can not be empty!!")
    @Column(nullable = false)
    @Pattern(regexp = "(?![\\s.]+$)[a-zA-Z\\s.]{1,20}", message = "First name can only contains letters and must be between 1-20 characters.")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty!!")
    @Column(nullable = false)
    @Pattern(regexp = "(?![\\s.]+$)[a-zA-Z\\s.]{1,20}", message = "Last name can only contains letters and must be between 1-20 characters.")
    private String lastName;

    @NotNull
    @Size(max = 10, min = 10, message = "Phone number should be of 10 digits")
    @Pattern(regexp = "[2-9][0-9]{9}", message = "Phone number is invalid!!")
    private String phone;

    @NotNull
    @NotEmpty(message = "Driver License cannot be empty")
    private String driverLicense;

    private String apt;

    @NotNull
    @Pattern(regexp = "[#.0-9a-zA-Z\\s,-]+", message = "Street is invalid!!")
    private String street;

    @NotNull
    @Pattern(regexp = "[a-zA-Z',.\\s-]{1,25}", message = "City is invalid!!")
    private String city;

    @NotNull
    @Pattern(regexp = "(?:AB|BC|MB|N[BLTSU]|ON|PE|QC|SK|YT|Alberta|British Columbia|Manitoba|New Brunswick|Newfoundland and Labrador|Nova Scotia|Ontario|Prince Edward Island|Quebec|Saskatchewan)*", message = "Province must be in Canada and have 2 characters.")
    private String province;

    @NotNull
    @Pattern(regexp = "(?!.*[dfioquDFIOQU])[a-vxyA-VXY][0-9][a-zA-Z] ?[0-9][a-zA-Z][0-9]", message = "Postal Code is invalid!!")
    private String postalCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}