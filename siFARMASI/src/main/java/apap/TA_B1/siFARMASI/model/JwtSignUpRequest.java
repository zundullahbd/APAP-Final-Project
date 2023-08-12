package apap.TA_B1.siFARMASI.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtSignUpRequest implements Serializable {
    private String username;
    private String password;
    private String email;
    private String name;
    private String role;

}
