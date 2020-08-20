package com.kvarek.registration.validation;

public class Authorities {


    String[] noAuthoritiesPathArray;
    String[] coachAuthoritiesPathArray;
    String[] athleteAuthoritiesPathArray;


    public Authorities() {
        this.noAuthoritiesPathArray= new String[]{"/saveCoach", "/login", "/person/updateAthlete"};
        this.coachAuthoritiesPathArray = new String[]{"/exercise", "/person"};
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
