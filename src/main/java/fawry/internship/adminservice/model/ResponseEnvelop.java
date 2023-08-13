package fawry.internship.adminservice.model;

import fawry.internship.adminservice.exception.ErrorDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEnvelop {
    private boolean success;
    private Object data;
    private ErrorDetails errorDetails;
}
