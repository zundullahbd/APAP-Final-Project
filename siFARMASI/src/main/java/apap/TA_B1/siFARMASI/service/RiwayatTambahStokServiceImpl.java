package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.RiwayatTambahStokModel;
import apap.TA_B1.siFARMASI.repository.RiwayatTambahStokDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class RiwayatTambahStokServiceImpl implements  RiwayatTambahStokService {
    @Autowired
    RiwayatTambahStokDb riwayatTambahStokDb;

    @Autowired
    private UserService userService;

    @Override
    public void addRiwayat(RiwayatTambahStokModel riwayatTambahStok){
        riwayatTambahStok.setId_user(userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        riwayatTambahStok.setCreated_at(LocalDateTime.now());
        riwayatTambahStokDb.save(riwayatTambahStok);
    }
}
