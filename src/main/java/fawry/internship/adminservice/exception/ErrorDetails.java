package fawry.internship.adminservice.exception;

import lombok.Data;

@Data
public class ErrorDetails
{

    private Integer code;
    private long timeStamp;
    private Object details;
}
