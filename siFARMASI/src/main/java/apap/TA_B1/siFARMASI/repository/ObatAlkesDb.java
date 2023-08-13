package apap.TA_B1.siFARMASI.repository;


import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObatAlkesDb extends JpaRepository<ObatAlkesModel, Integer> {
    ObatAlkesModel getObatAlkesById(Integer id);
}
