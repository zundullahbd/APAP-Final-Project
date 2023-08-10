package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.repository.ObatAlkesDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObatAlkesServiceImpl implements ObatAlkesService {
    @Autowired
    ObatAlkesDb obatAlkesDb;

    @Override
    public List<ObatAlkesModel> getListObatAlkes() {
        return obatAlkesDb.findAll();
    }

    @Override
    public ObatAlkesModel getObatAlkesById(Integer id) {
        return obatAlkesDb.findById(id).orElse(null);
    }

    @Override
    public ObatAlkesModel addObatAlkes(ObatAlkesModel obatAlkes) {
        return obatAlkesDb.save(obatAlkes);
    }

    @Override
    public ObatAlkesModel updateObatAlkes(ObatAlkesModel obatAlkes) {
        return obatAlkesDb.save(obatAlkes);
    }

    @Override
    public void deleteObatAlkes(Integer id) {
        ObatAlkesModel obatAlkes = obatAlkesDb.findById(id).orElse(null);
        if (obatAlkes != null) {
            obatAlkesDb.delete(obatAlkes);
        }
    }

    @Override
    public void reduceStock(ObatAlkesModel obatAlkes, Integer jumlah) {
        obatAlkes.setStok(obatAlkes.getStok() - jumlah);
        obatAlkesDb.save(obatAlkes);
    }

    @Override
    public void increaseStock(ObatAlkesModel obatAlkes, Integer jumlah) {
        obatAlkes.setStok(obatAlkes.getStok() + jumlah);
        obatAlkesDb.save(obatAlkes);
    }

    @Override
    public ObatAlkesModel getObatAlkesById(Long id){
        return obatAlkesDb.getObatAlkesById(id);
    }

    public void deleteObatAlkes(ObatAlkesModel obatAlkes){
        obatAlkesDb.delete(obatAlkes);
    }

}
