package apap.TA_B1.siFARMASI.repository;

import apap.TA_B1.siFARMASI.model.AdminModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDb extends JpaRepository<AdminModel, String> {
    Optional<AdminModel> findByUsername(String username);
}
