package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.ResepModel;
import apap.TA_B1.siFARMASI.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ResepServiceImpl implements ResepService {
    @Autowired
    ResepDb resepDb;

    @Autowired
    private UserService userService;

    @Override
    public void addResep(ResepModel resep){
        LocalDateTime now = LocalDateTime.now();
        resep.setId_user(userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));

        // Generating custom resep number
        String prefix = "R-";
        String suffix = "";
        Long id = resepDb.count() + 1;
        suffix = id.toString();

        resep.setNomor(prefix + suffix);
        resep.setCreated_at(now);
        resepDb.save(resep);
    }
}
