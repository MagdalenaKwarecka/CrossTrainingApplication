package com.kvarek.registration.validation;

import org.springframework.stereotype.Component;

@Component
public class Authorities {


    String[] noAuthoritiesPathArray;
    String[] coachAuthoritiesPathArray;
    String[] athleteAuthoritiesPathArray;


    public Authorities() {
        this.noAuthoritiesPathArray= new String[]{
                "registration/saveCoach",
                "registration/login"};
        this.coachAuthoritiesPathArray = new String[]{
                "/exercise/initial",
                "/exercise/save",
                "/exercise/delete",
                "/exercise/findAllSortedByName",
                "/exercise/findAllByNameContaining",
                "/exerciseExecution/save",
                "/person/saveAthlete",
                "/person/findByLoginOrEmail",
                "/person/findAllByLastNameContaining",
                "/round/save",
                "/wod/save",
                "/wodExecution/save",
                "/wodExecution/findAllByAthlete"
        };
        this.athleteAuthoritiesPathArray = new String[]{
                "/exercise/findAllByNameContaining",
                "/person/updateAthlete",
                "/wodExecution/update"};
    }

    public String[] getNoAuthoritiesPathArray() {
        return noAuthoritiesPathArray;
    }
    public String[] getCoachAuthoritiesPathArray() {
        return coachAuthoritiesPathArray;
    }
    public String[] getAthleteAuthoritiesPathArray() {
        return athleteAuthoritiesPathArray;
    }


}
