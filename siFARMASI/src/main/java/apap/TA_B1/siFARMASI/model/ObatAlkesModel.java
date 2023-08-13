package apap.TA_B1.siFARMASI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ObatAlkes")
@Entity
public class ObatAlkesModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMitra", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MitraModel idMitra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apoteker", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ObatAlkesModel apoteker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dokter", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ObatAlkesModel dokter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ObatAlkesModel manager;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 50)
    @Column(name = "kategoriObat", nullable = false)
    private String kategoriObat;

    @NotNull
    @Size(max = 50)
    @Column(name = "jenisSediaan", nullable = false)
    private String jenisSediaan;

    @NotNull
    @Size(max = 50)
    @Column(name = "kemasanSimpan", nullable = false)
    private String kemasanSimpan;

    @NotNull
    @Size(max = 50)
    @Column(name = "kemasanBeli", nullable = false)
    private String kemasanBeli;

    @NotNull
    @Column(name = "hargaBeli", nullable = false)
    private Integer hargaBeli;

    @NotNull
    @Column(name = "hargaJual", nullable = false)
    private Integer hargaJual;

    @NotNull
    @Column(name = "stok", nullable = false)
    private Integer stok;

    @Column(name = "kadaluwarsa", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate kadaluwarsa;

    @NotNull
    @Size(max = 50)
    @Column(name = "lokasi", nullable = false)
    private String lokasi;

    @NotEmpty(message = "Path file tidak boleh kosong")
    @Column(name = "pathFile")
    private String pathFile;

    @OneToMany(mappedBy = "id_obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RiwayatTambahStokModel> listRiwayat;

    @OneToMany(mappedBy = "id_obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResepModel> listResep;
}
