package apap.TA_B1.siFARMASI.repository;

import apap.TA_B1.siFARMASI.model.MitraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MitraDb extends JpaRepository<MitraModel, Integer> {

}
