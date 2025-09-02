package com.bci.users.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
public class ApplicationProperties {

    @Value("${application.regex.email}")
    private String emailRegex;
    
    @Value("${application.regex.password}")
    private String passwordRegex;

    public String getEmailRegex() {
        return emailRegex;
    }

    public String getPasswordRegex() {
        return passwordRegex;
    }

}
