package com.saletax.service;

    /*
    Tax Percentage value constant and the commodities on which it is not applicable
    */

enum TaxPercentageConstants{
    BASICSALETAX("10"),
    IMPORTDUTY("5");

    private String taxPercent;

    public Float getTaxPercent(){
        return Float.valueOf(this.taxPercent);
    }

    private TaxPercentageConstants(String taxPercent){
        this.taxPercent=taxPercent;
    }

}
