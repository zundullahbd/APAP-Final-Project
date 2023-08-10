package apap.TA_B1.siFARMASI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mitra")
@Entity
public class MitraModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotBlank
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotBlank
    @Column(name = "jenis", nullable = false)
    private String jenis;

    @NotBlank
    @Column(name = "kontak", nullable = false)
    private String kontak;

    @Column(name = "tanggalKerjasama")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalKerjasama;

    @OneToMany(mappedBy = "idMitra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ObatAlkesModel> obatAlkesList;

    @OneToMany(mappedBy = "idMitra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<RiwayatTambahStokModel> riwayatTambahStokList;
}
