package fawry.internship.adminservice.repository;

import fawry.internship.adminservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long>
{
    Optional<Admin> findAdminByEmail(String email);
    List<Admin> findAllByEnabled(boolean statues);
    boolean existsAdminByEmail(String email);
}
