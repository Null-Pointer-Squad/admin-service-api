package fawry.internship.adminservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
}
