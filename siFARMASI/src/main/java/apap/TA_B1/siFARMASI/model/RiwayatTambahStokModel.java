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
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RiwayatTambahStok")
@Entity
public class RiwayatTambahStokModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obat", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ObatAlkesModel id_obat;

    @NotNull
    @Column(name = "id_mitra", nullable = false)
    private Integer id_mitra;

    @NotNull
    @Column(name = "jumlah_obat", nullable = false)
    private Integer jumlah_obat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel id_user;

    @Column(name = "created_at", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm")
    private LocalDateTime created_at;
}
