package apap.TA_B1.siFARMASI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 50)
    @Column(name = "role", nullable = false)
    private String role;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "created_at_timestamp", nullable = false)
    private Instant created_at_timestamp;

    @OneToMany(mappedBy = "id_user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResepModel> listResep;

    @OneToMany(mappedBy = "id_user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RiwayatTambahStokModel> listRiwayat;
}
