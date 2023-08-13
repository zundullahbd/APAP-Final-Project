package apap.TA_B1.siFARMASI.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtLoginRequest implements Serializable {
    private String username;
    private String password;
}
