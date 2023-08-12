package apap.TA_B1.siFARMASI.repository;

import apap.TA_B1.siFARMASI.model.ApotekerModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, String>{
}
