package apap.TA_B1.siFARMASI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DokterModel extends UserModel implements Serializable {

}
