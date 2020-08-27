package com.kvarek.registration.validation;

import org.springframework.stereotype.Component;

@Component
public class Authorities {


    String[] noAuthoritiesPathArray;
    String[] coachAuthoritiesPathArray;
    String[] athleteAuthoritiesPathArray;



    public Authorities() {
        this.noAuthoritiesPathArray= new String[]
                {"/registration", "/saveCoach", "/login", "/person/updateAthlete", "/exerciseExecution/save", "/exercise/initial", "/round/save", "/round/findById"};
        this.coachAuthoritiesPathArray = new String[]
                {"/person"};
        this.athleteAuthoritiesPathArray = new String[]{"/exercise/findAllByNameContaining"};
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
