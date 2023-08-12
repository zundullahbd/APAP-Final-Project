package apap.TA_B1.siFARMASI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DokterModel extends UserModel implements Serializable {
    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ObatAlkesModel> listObatAlkes;
}
