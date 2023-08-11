package apap.TA_B1.siFARMASI.repository;

import apap.TA_B1.siFARMASI.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDb extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
    List<UserModel> findAllByRole(String role);
}
