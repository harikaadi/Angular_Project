package com.amaze_care;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/token").permitAll()
                        .requestMatchers("/auth/signup").permitAll()
                        .requestMatchers("/admin/hello").hasRole("ADMIN")
                        .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/doctor/add").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/doctor/specialization/{specialization}").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/doctor/available").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/doctor/schedule/add/{doctorId}").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/inpatient/add").hasAnyRole("PATIENT", "ADMIN")
                        .requestMatchers("/healthissue/add").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/room/add").hasAnyRole("ADMIN")
                        .requestMatchers("/room/avail").hasAnyRole("ADMIN")
                        .requestMatchers("/addadmission/{patientid}/{issueName}/{roomtype}").hasAnyRole("PATIENT","ADMIN")
                        .requestMatchers("/patient-opd/add").hasAnyRole("PATIENT", "ADMIN")
                        .requestMatchers("/book-appointment/{patientId}/{doctorId}").hasAnyRole("PATIENT", "ADMIN")
                        .requestMatchers("/executive/add").hasAnyRole("ADMIN")
                        .requestMatchers("/test/add").hasAnyRole("ADMIN","DOCTOR","EXECUTIVE")
                        .requestMatchers("/inpatient-test/add/{patientid}/{doctorid}/{testid}").hasAnyRole("ADMIN","DOCTOR","EXECUTIVE")
                        .requestMatchers("/testresult/add/{inpatienttestid}").hasAnyRole("ADMIN","DOCTOR","EXECUTIVE")
                        .requestMatchers("/opd-test/add/{patientOPDId}/{doctorId}/{testId}").hasAnyRole("ADMIN","DOCTOR","EXECUTIVE")
                        .requestMatchers("/opdtestresult/add/{opdTestId}").hasAnyRole("ADMIN","DOCTOR","EXECUTIVE")
                        .requestMatchers("/add-prescription/{opdPatientId}/{doctorId}").hasAnyRole("ADMIN","DOCTOR","EXECUTIVE")
                        .requestMatchers("/inpatient-add-prescription/{InPatientId}/{doctorId}").hasAnyRole("ADMIN","DOCTOR","EXECUTIVE")
                        
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
     AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
     DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
