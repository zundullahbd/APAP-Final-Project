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
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ObatAlkes")
@Entity
public class ObatAlkesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mitra", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ObatAlkesModel id_mitra;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 50)
    @Column(name = "kategori_obat", nullable = false)
    private String kategori_obat;

    @NotNull
    @Size(max = 50)
    @Column(name = "jenis_sediaan", nullable = false)
    private String jenis_sediaan;

    @NotNull
    @Size(max = 50)
    @Column(name = "kemasan_simpan", nullable = false)
    private String kemasan_simpan;

    @NotNull
    @Size(max = 50)
    @Column(name = "kemasan_beli", nullable = false)
    private String kemasan_beli;

    @NotNull
    @Column(name = "harga_beli", nullable = false)
    private Integer harga_beli;

    @NotNull
    @Column(name = "harga_jual", nullable = false)
    private Integer harga_jual;

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
    @Column(name = "path_file", nullable = false)
    private String path_file;

    @OneToMany(mappedBy = "id_obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<RiwayatTambahStokModel> listRiwayat;

    @OneToMany(mappedBy = "id_obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ResepModel> listResep;
}
