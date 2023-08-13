package apap.TA_B1.siFARMASI.service;

import java.util.List;
import apap.TA_B1.siFARMASI.model.ApotekerModel;

public interface ApotekerService {
    List<ApotekerModel> viewAllApoteker();
    void addApoteker(ApotekerModel apoteker);
    String encrypt(String pasword);
    ApotekerModel findByUsername(String apoteker);
    ApotekerModel getApotekerLoggedIn();
}
