package apap.TA_B1.siFARMASI.security.jwt;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint{

    private static Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    private static final long serialVersionUID = -7858869558953243875L;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException{
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ERROR: Unauthorized");
    }
}