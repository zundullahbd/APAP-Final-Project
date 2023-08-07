package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.ObatAlkesModel;

import java.util.List;

public interface ObatAlkesService {
    List<ObatAlkesModel> getListObatAlkes();
    void reduceStock(ObatAlkesModel obatAlkes, Integer jumlah);
    void increaseStock(ObatAlkesModel obatAlkes, Integer jumlah);
}
