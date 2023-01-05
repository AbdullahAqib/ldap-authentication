package com.itachay.ldapauthentication.repository;

import com.itachay.ldapauthentication.domain.LDAPServerConfiguration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LDAPServerConfigurationRepository {

    public List<LDAPServerConfiguration> getAllConfigurations(){
        return new ArrayList<LDAPServerConfiguration>(
                Arrays.asList(
                        new LDAPServerConfiguration("uid={0},ou=people", "ldap://localhost:8389/dc=springframework,dc=org"),
                        new LDAPServerConfiguration("uid={0},ou=people", "ldap://localhost:10389/dc=example,dc=com")
                ));
    }
}