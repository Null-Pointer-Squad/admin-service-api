package fawry.internship.adminservice.model;

//TODO convert all models to records

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
public class LoginRequest {
    @NotBlank(message = "email is mandatory")
    @Email(message = "invalid email form")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;

}
