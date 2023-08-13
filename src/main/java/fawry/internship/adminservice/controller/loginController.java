package fawry.internship.adminservice.controller;

import fawry.internship.adminservice.model.LoginRequest;

import fawry.internship.adminservice.model.ResponseEnvelop;
import fawry.internship.adminservice.model.ResponseFactory;
import fawry.internship.adminservice.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
@RequiredArgsConstructor
@CrossOrigin
public class loginController {

    private final LoginService loginService;


    @PostMapping
    public ResponseEntity<ResponseEnvelop> login(@RequestBody @Valid LoginRequest request)
    {
        ResponseEnvelop response  = ResponseFactory.getSuccessResponse();
        response.setData(loginService.authenticate(request));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
