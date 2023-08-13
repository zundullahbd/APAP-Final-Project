package apap.TA_B1.siFARMASI.repository;

import apap.TA_B1.siFARMASI.model.ManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDb extends JpaRepository<ManagerModel, String> {
}
