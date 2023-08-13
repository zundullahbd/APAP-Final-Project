package apap.TA_B1.siFARMASI.repository;

import apap.TA_B1.siFARMASI.model.ResepModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResepDb extends JpaRepository<ResepModel, Integer> {

}
