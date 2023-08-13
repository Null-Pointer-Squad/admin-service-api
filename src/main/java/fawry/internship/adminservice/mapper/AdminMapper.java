package fawry.internship.adminservice.mapper;

import fawry.internship.adminservice.entity.Admin;
import fawry.internship.adminservice.model.AdminAddRequest;
import fawry.internship.adminservice.model.AdminModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    AdminModel toModel(Admin admin);
    Admin toEntity(AdminAddRequest request);
    List<AdminModel> toModel(List<Admin> admin);
}
