package com.arma.inz.compcal.users.enums;

public enum VatFormEnum {
    LINE_19("Podatek liniowy 19%")

    ;
    private final String label;
    VatFormEnum(String label){
        this.label = label;
    }
    @Override
    public String toString() {
        return this.label;
    }
}
