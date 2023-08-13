package apap.TA_B1.siFARMASI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

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

    @Column(name = "kadaluwarsa", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm")
    private LocalDateTime kadaluwarsa;

    @NotNull
    @Size(max = 50)
    @Column(name = "lokasi", nullable = false)
    private String lokasi;

    @NotNull
    @Size(max = 50)
    @Column(name = "pathFile", nullable = false)
    private String pathFile;

    @OneToMany(mappedBy = "id_obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RiwayatTambahStokModel> listRiwayat;

    @OneToMany(mappedBy = "id_obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResepModel> listResep;
}
