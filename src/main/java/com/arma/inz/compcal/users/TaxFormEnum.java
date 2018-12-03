package com.arma.inz.compcal.users;

public enum TaxFormEnum {
    OnGeneralRules("Na zasadach ogólnych"),

    ;

    private final String label;
    TaxFormEnum(String label){
        this.label = label;
    }
    @Override
    public String toString() {
        return this.label;
    }
}
