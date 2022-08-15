package com.bulkup.health.config.spring_security;


public enum SecurityRole {
    USER("ROLE_USER"),
    TRAINER("ROLE_TRAINER");
    private final String myRole;
    SecurityRole(final String myRole) {
        this.myRole = myRole;
    }
    @Override
    public String toString() {
        return myRole;
    }
}
