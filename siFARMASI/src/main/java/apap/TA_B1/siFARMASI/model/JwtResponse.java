package apap.TA_B1.siFARMASI.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String username;

    public JwtResponse(String jwttoken, String username) {
        this.username = username;
        this.token = jwttoken;
    }
}

