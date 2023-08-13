package fawry.internship.adminservice.controller;

import fawry.internship.adminservice.model.AdminAddRequest;
import fawry.internship.adminservice.model.ResponseEnvelop;
import fawry.internship.adminservice.model.ResponseFactory;
import fawry.internship.adminservice.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admins")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController
{
    private final AdminService adminService;
    @PostMapping
    public ResponseEntity<ResponseEnvelop> addAdmin(@RequestBody @Valid AdminAddRequest request)
    {
        adminService.add(request);

        return new ResponseEntity<>(ResponseFactory.getSuccessResponse(),HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<ResponseEnvelop> getAdmins()
    {
        ResponseEnvelop response = ResponseFactory.getSuccessResponse();
        response.setData(adminService.listAll());

        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    @PutMapping(path = "/{id}/activate")
    public ResponseEntity<ResponseEnvelop> activate(@PathVariable(name = "id") Long id)
    {
        adminService.activate(id);
        return new ResponseEntity<>(ResponseFactory.getSuccessResponse(),HttpStatus.OK);

    }
    @PutMapping(path = "/{id}/deactivate")
    public ResponseEntity<ResponseEnvelop> deactivate(@PathVariable(name = "id") Long id)
    {
        adminService.deactivate(id);
        return new ResponseEntity<>(ResponseFactory.getSuccessResponse(),HttpStatus.OK);

    }



}
