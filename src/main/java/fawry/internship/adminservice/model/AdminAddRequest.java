package fawry.internship.adminservice.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAddRequest
{
    @NotBlank(message = "firstname is mandatory")
    @Length(max = 50)
    private String firstName;
    @NotBlank(message = "lastname is mandatory")
    @Length(max = 50)
    private String lastName;
    @NotBlank(message = "email is mandatory")
    @Length(max = 100)
    @Email(message = "invalid email form")
    private String email;
    @NotBlank(message = "password is mandatory")
    @Length(max = 30,min = 5)
    private String password;
}
