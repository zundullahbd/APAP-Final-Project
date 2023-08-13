package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.MitraModel;

import java.util.List;

public interface MitraService {
    List<MitraModel> getListMitra();
    MitraModel getMitraById(Integer id);
    MitraModel addMitra(MitraModel mitra);
    MitraModel updateMitra(MitraModel mitra);
    void deleteMitra(Integer id);

}
