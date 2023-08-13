package fawry.internship.adminservice.service;

import fawry.internship.adminservice.model.AdminAddRequest;
import fawry.internship.adminservice.model.AdminModel;

import java.util.List;

public interface AdminService {

    void add(AdminAddRequest request);
    void activate(Long id);
    void deactivate(Long id);
    List<AdminModel> listAll();


}
