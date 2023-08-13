package fawry.internship.adminservice.services;

import fawry.internship.adminservice.entity.Admin;
import fawry.internship.adminservice.exception.ErrorCode;
import fawry.internship.adminservice.exception.RuntimeErrorCodedException;
import fawry.internship.adminservice.mapper.AdminMapper;
import fawry.internship.adminservice.model.AdminAddRequest;
import fawry.internship.adminservice.model.AdminModel;
import fawry.internship.adminservice.repository.AdminRepository;
import fawry.internship.adminservice.service.AdminService;
import fawry.internship.adminservice.service.implementation.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class AdminServiceTest {

    @Mock
    private  AdminRepository adminRepository;
    @Mock
    private AdminMapper adminMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    private AdminService adminService;

    @BeforeEach
     void setUp()
    {
        adminService = new AdminServiceImpl(adminRepository,adminMapper,passwordEncoder);
    }

    @Test
     void GivenNewEmail_WhenRegistration_ThenRegisterSuccessfully()
    {
        AdminAddRequest request = new AdminAddRequest("ahmed","ali","ahmedHany@gmail.com","123456");

        Mockito.when(adminRepository.existsAdminByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(adminMapper.toEntity(request)).thenReturn(new Admin());

        adminService.add(request);
    }

    @Test
     void GivenUsedEmail_WhenRegistration_ThenThrowException()
    {
        AdminAddRequest request = new AdminAddRequest("ahmed","ali","ahmedHany@gmail.com","123456");

        Mockito.when(adminRepository.existsAdminByEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(adminMapper.
                toEntity(request)).thenReturn(new Admin());

       assertThatThrownBy(()->adminService.add(request))
               .isEqualTo(new RuntimeErrorCodedException(ErrorCode.EMAIL_ALREADY_EXISTS));
    }

    @Test
     void GivenValidId_WhenActivation_ThenActivateSuccessfully()
    {
        Admin admin =new Admin(1L,"ahmed","ali","ahmedAli@gmail.com","1234546",false);
        Mockito.when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        adminService.activate(1L);
        assertThat(admin.getEnabled()).isEqualTo(true);

    }
    @Test
    void GivenInvalidId_WhenActivation_ThenThrowException()
    {
        Admin admin =new Admin(1L,"ahmed","ali","ahmedAli@gmail.com","1234546",false);
        Mockito.when(adminRepository.findById(1L)).thenReturn(Optional.empty());


        assertThatThrownBy(()-> adminService.activate(1L)).isEqualTo(new RuntimeErrorCodedException(ErrorCode.USER_NOT_FOUND_EXCEPTION));

    }


    @Test
    void GivenValidId_WhenDeactivation_ThenDeactivateSuccessfully()
    {
        Admin admin =new Admin(1L,"ahmed","ali","ahmedAli@gmail.com","1234546",true);
        Mockito.when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        adminService.deactivate(1L);
        assertThat(admin.getEnabled()).isEqualTo(false);

    }
    @Test
    void GivenInvalidId_WhenDeactivation_ThenThrowException()
    {
        Admin admin =new Admin(1L,"ahmed","ali","ahmedAli@gmail.com","1234546",false);
        Mockito.when(adminRepository.findById(1L)).thenReturn(Optional.empty());


        assertThatThrownBy(()-> adminService.deactivate(1L)).isEqualTo(new RuntimeErrorCodedException(ErrorCode.USER_NOT_FOUND_EXCEPTION));

    }

    @Test
    void Given_WhenListingAll_ThenReturnsAll()
    {
        Admin admin1 =new Admin(1L,"ahmed","ali","ahmedAli@gmail.com","1234546",false);
        Admin admin2 =new Admin(2L,"ahmed","ali","ahmedAli@gmail.com","1234546",false);

        AdminModel adminModel1 = new AdminModel(1L,"ahmed","ali","ahmedAli@gmail.com",false);
        AdminModel adminModel2 = new AdminModel(2L,"ahmed","ali","ahmedAli@gmail.com",false);
        List<Admin> admins = List.of(admin1,admin2);
        List<AdminModel> adminsModel = List.of(adminModel1,adminModel2);

        Mockito.when(adminRepository.findAll()).thenReturn(admins);
        Mockito.when(adminMapper.toModel(admins)).thenReturn(adminsModel);
        assertThat(adminService.listAll()).isEqualTo(adminsModel);

    }




}
