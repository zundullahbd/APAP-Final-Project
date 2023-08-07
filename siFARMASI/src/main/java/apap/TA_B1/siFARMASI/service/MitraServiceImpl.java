package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.repository.MitraDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MitraServiceImpl implements MitraService {
    @Autowired
    MitraDb mitraDb;

    @Override
    public List<MitraModel> getListMitra() { return mitraDb.findAll(); }
}
