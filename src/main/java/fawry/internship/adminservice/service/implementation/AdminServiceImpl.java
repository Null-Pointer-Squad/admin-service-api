package fawry.internship.adminservice.service.implementation;

import fawry.internship.adminservice.entity.Admin;
import fawry.internship.adminservice.exception.ErrorCode;
import fawry.internship.adminservice.exception.RuntimeErrorCodedException;
import fawry.internship.adminservice.mapper.AdminMapper;
import fawry.internship.adminservice.model.AdminAddRequest;
import fawry.internship.adminservice.model.AdminModel;
import fawry.internship.adminservice.repository.AdminRepository;
import fawry.internship.adminservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void add(AdminAddRequest request)
    {
        if (adminRepository.existsAdminByEmail(request.getEmail()))
            throw new RuntimeErrorCodedException(ErrorCode.EMAIL_ALREADY_EXISTS);

        Admin admin = adminMapper.toEntity(request);
        admin.setEnabled(true);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);


    }

    @Override
    public void activate(Long id)
    {
        changeActiveStatues(id,true);
    }

    @Override
    public void deactivate(Long id)
    {
       changeActiveStatues(id,false);
    }

    private void changeActiveStatues(Long id,boolean statues)
    {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(()-> new RuntimeErrorCodedException(ErrorCode.USER_NOT_FOUND_EXCEPTION));
        admin.setEnabled(statues);
        adminRepository.save(admin);

    }

    @Override
    public List<AdminModel> listAll() {

        return adminMapper.toModel(adminRepository.findAll());
    }



}
