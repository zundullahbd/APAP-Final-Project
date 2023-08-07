package apap.TA_B1.siFARMASI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
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

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 100)
    @Column(name = "role", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 50)
    @Column(name = "email", nullable = false)
    private String jenis;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false)
    private String kontak;

    @Column(name = "tanggal_kerjasama", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm")
    private LocalDateTime tanggal_kerjasama;

    @OneToMany(mappedBy = "id_mitra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ObatAlkesModel> listObatAlkes;
}
