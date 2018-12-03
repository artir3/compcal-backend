package com.arma.inz.compcal.users;

public enum CurrencyEnum {
    PLN("zł"),
    EUR("euro"),
    DOL("dolary"),
    ;

    private final String label;
    CurrencyEnum(String label){
        this.label = label;
    }
    @Override
    public String toString() {
        return this.label;
    }
}
