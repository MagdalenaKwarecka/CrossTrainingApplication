package com.kvarek.registration.validation;

import org.springframework.stereotype.Component;

@Component
public class Authorities {


    String[] noAuthoritiesPathArray;
    String[] coachAuthoritiesPathArray;
    String[] athleteAuthoritiesPathArray;


    public Authorities() {
        this.noAuthoritiesPathArray= new String[]{"/saveCoach", "/login"};
        this.coachAuthoritiesPathArray = new String[]{"/exercise/initial", "/person/saveAthlete"};
        this.athleteAuthoritiesPathArray = new String[]{"/exercise/findAllByNameContaining", "/person/updateAthlete"};
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
