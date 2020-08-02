package com.saletax.service;

import com.saletax.model.STResponseObject;

public class TaxCalculator implements STApplicationRules{
    static String[][] cleanData;
    public TaxCalculator(String[][] cleanData){
        TaxCalculator.cleanData=cleanData;
    }
    @Override
    public STResponseObject rule(String[] data) {
        Float[] saleTaxEachItem=new Float[data.length];
        Float[] importDutyEachItem=new Float[data.length];

        initializeByZero(saleTaxEachItem,importDutyEachItem);

        STResponseObject response=new STResponseObject();

        if(!checkDataObtained(cleanData)) {
            response.setData(new String[]{"Input is not correctly written"});
            return response;
        }

        //Sale Tax Calculation
        saleTaxEachItem = updateSaleTax(saleTaxEachItem,data,cleanData);
        //Import Duty Calculation
        importDutyEachItem = updateImportDuty(importDutyEachItem,data,cleanData);

        //Add data All Data to Response Object
        response = updateData(data,saleTaxEachItem,importDutyEachItem,cleanData,response);
        //Round Off
        response.setTotalBill(Float.parseFloat(String.format("%.2f",response.getTotalBill())));
        return response;
    }

    private void initializeByZero(Float[] arrayOne,Float[] arrayTwo){
        for(int i=0;i<arrayOne.length;i++) {
            arrayOne[i] = 0.0f;
            arrayTwo[i] = 0.0f;
        }
    }
    private boolean checkDataObtained(String[][] cleanData) {
        for(int i=0;i<cleanData.length;i++)
            if(cleanData[i][0]==null || cleanData[i][1]==null || cleanData[i][2]==null)
                return false;
        return true;
    }

    public STResponseObject updateData(String[] data,Float[] saleTaxEachItem,Float[] importDutyEachItem,String[][] cleanData,STResponseObject response){
        Float curItemTotalTax=0.0f,curItemTotalAmount=0.0f,totalBillAmount=0.0f,totalTaxAmount=0.0f;
        for(int ind =0;ind<data.length;ind++){
            curItemTotalTax=roundOff(saleTaxEachItem[ind]+importDutyEachItem[ind]);
            curItemTotalAmount=(Integer.parseInt(cleanData[ind][1])*Float.parseFloat(cleanData[ind][2]))+curItemTotalTax;
            totalTaxAmount+=curItemTotalTax;
            totalBillAmount+=curItemTotalAmount;
            data[ind]=data[ind].substring(0,data[ind].lastIndexOf(" at "))+": "+String.format("%.2f",curItemTotalAmount);
        }
        response.setData(data);
        response.setSaleTax(totalTaxAmount);
        response.setTotalBill(totalBillAmount);
        return response;
    }

    public Float[] updateSaleTax(Float[] saleTaxEachItem,String[] data, String[][] cleanData){
        for(int i=0;i<data.length;i++)
            if(cleanData[i][0].equalsIgnoreCase("book")
                    || cleanData[i][0].equalsIgnoreCase("food")
                    || cleanData[i][0].equalsIgnoreCase("medicine"))
                continue;
                /*response.setTotalBill(response.getTotalBill()+
                        (Integer.parseInt(cleanData[i][1])*Float.parseFloat(cleanData[i][2])));*/
            else {
                saleTaxEachItem[i]=(Integer.parseInt(cleanData[i][1])*Float.parseFloat(cleanData[i][2])*TaxPercentageConstants.BASICSALETAX.getTaxPercent())/100;
                /*float prevSaleTax=response.getSaleTax();
                *//*response.setTotalBill(prevTotalBill+
                        ((Integer.parseInt(cleanData[i][1])*Float.parseFloat(cleanData[i][2])*(100+TaxPercentageConstants.BASICSALETAX.getTaxPercent()))/100));*//*
                response.setSaleTax(prevSaleTax+
                        ((Integer.parseInt(cleanData[i][1])*Float.parseFloat(cleanData[i][2])*TaxPercentageConstants.BASICSALETAX.getTaxPercent())/100));*/
            }
        return saleTaxEachItem;
    }

    public Float[] updateImportDuty(Float[] importDuty,String[] data, String[][] cleanData) {
        for(int i=0;i<data.length;i++)
            if(cleanData[i][3].equalsIgnoreCase("true")){
                importDuty[i]=(Integer.parseInt(cleanData[i][1])*Float.parseFloat(cleanData[i][2])*TaxPercentageConstants.IMPORTDUTY.getTaxPercent())/100;
                /*float prevTotalBill=response.getTotalBill();
                float prevSaleTax=response.getSaleTax();
                response.setTotalBill(prevTotalBill+
                        (Integer.parseInt(cleanData[i][1])*Float.parseFloat(cleanData[i][2])*TaxPercentageConstants.IMPORTDUTY.getTaxPercent())/100);
                response.setSaleTax(prevSaleTax+
                        (Integer.parseInt(cleanData[i][1])*Float.parseFloat(cleanData[i][2])*TaxPercentageConstants.IMPORTDUTY.getTaxPercent())/100);*/
            }
        return importDuty;
    }

    public float roundOff(float num){
        num=Float.parseFloat(String.format("%.2f",num));
        return (int)num+(5*((float)Math.ceil(((num*100)%100)/5))/100);
    }
}
