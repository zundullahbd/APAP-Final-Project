package apap.TA_B1.siFARMASI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    String admin = "ADMIN";
    String apoteker = "APOTEKER";
    String dokter = "DOKTER";
    String manajemen = "MANAJEMEN";


    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/", "/signup").permitAll() // Allow public access to home and signup
                .antMatchers("/login-sso", "/validate-ticket").permitAll()
                .antMatchers("/user").permitAll()
                .antMatchers("/user/adminAddUser").permitAll()
                .antMatchers("/user/manajemenUser").hasAuthority(admin)
                .antMatchers("/user/view/**").hasAuthority(admin)
                .antMatchers("/obat-alkes/**").hasAnyAuthority(apoteker)
                .antMatchers("/obat-alkes/input-obat-alkes").hasAnyAuthority(dokter, apoteker)
                .antMatchers("/resep/input-resep").hasAnyAuthority(dokter, apoteker)
                .antMatchers("/laporan/**").hasAnyAuthority(admin, manajemen)
                .antMatchers("/mitra/**").hasAnyAuthority(admin, apoteker)
                .antMatchers("/resep/input-resep").hasAnyAuthority(dokter, apoteker)
                .antMatchers("/obat-alkes/input-obat-alkes").hasAnyAuthority(dokter, apoteker)
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login"))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login").permitAll())
                .sessionManagement(management -> management
                        .sessionFixation().newSession().maximumSessions(1));
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
