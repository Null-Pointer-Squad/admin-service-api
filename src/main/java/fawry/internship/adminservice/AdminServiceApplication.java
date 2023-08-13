package fawry.internship.adminservice;

import fawry.internship.adminservice.entity.Admin;
import fawry.internship.adminservice.model.AdminAddRequest;
import fawry.internship.adminservice.repository.AdminRepository;
import fawry.internship.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@CrossOrigin

public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }


    @Bean
    @Autowired
    CommandLineRunner runner(AdminService service)
    {
        return (x)-> service.add(new AdminAddRequest("root","admin","admin@fawry.com","123456"));

    }

}

//TODO add global exception handling
//TODO add validation for all dto
//TODO optimise database using indexes and apply constrains
//TODO add unit testing layer using junit and mockito
//TODO isolate all constant values from code
//TODO make frontend using angular
//TODO how to deploy your app
//TODO add role based authentication

