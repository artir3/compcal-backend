package com.arma.inz.compcal.users.enums;

public enum RolesEnum {
    USER("user");

    private final String label;

    RolesEnum(String label){
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
