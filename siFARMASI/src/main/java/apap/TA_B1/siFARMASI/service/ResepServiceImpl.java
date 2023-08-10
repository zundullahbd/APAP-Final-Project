package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.ResepModel;
import apap.TA_B1.siFARMASI.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ResepServiceImpl implements ResepService {
    @Autowired
    ResepDb resepDb;

    @Override
    public void addResep(ResepModel resep){
        LocalDateTime now = LocalDateTime.now();
        resep.setCreated_at(now);
        resepDb.save(resep);
    }


}
