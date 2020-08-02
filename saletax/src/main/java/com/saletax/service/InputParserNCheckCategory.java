package com.saletax.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.saletax.dao.CommoditiesRepository;
import com.saletax.model.Commodities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
public class InputParserNCheckCategory {
    CommoditiesRepository comRepository = STService.getBean(CommoditiesRepository.class);
    @Getter @Setter String data;

    public InputParserNCheckCategory(String data){
        this.setData(data);
    }

    public String[][] cleanData(String[] data){
        String[][] result=new String[data.length][4];//Array having category, qty, price, imported boolean variable
        for(int ind=0;ind<data.length;ind++){
            String[] wordsInSentence=data[ind].split(" ");
            // getting category
            for(int curWordInd=0;curWordInd<wordsInSentence.length;curWordInd++){
                if(wordsInSentence[curWordInd].matches("^[0-9]$"))
                    continue;
                Optional<Commodities> record=comRepository.getByItem(wordsInSentence[curWordInd].toLowerCase());
                if(record.isPresent()){
                    result[ind][0]=record.get().getCategory();
                    System.out.println(result[ind][0]);
                    break;
                }
            }
            //getting quantity
            for(int curWordInd=0;curWordInd<wordsInSentence.length;curWordInd++){
                try {
                    result[ind][1]=String.valueOf(Integer.parseInt(wordsInSentence[curWordInd]));
                    System.out.println(result[ind][1]);
                    break;
                }catch (NumberFormatException nfe ){
                    continue;
                }
            }
            //getting price
            for(int curWordInd=wordsInSentence.length-1;curWordInd>=0;curWordInd--){
                try {
                    String.valueOf(Float.parseFloat(wordsInSentence[curWordInd]));
                    if(curWordInd>0 && wordsInSentence[curWordInd-1].equalsIgnoreCase("at")){
                        result[ind][2]=String.valueOf(Float.parseFloat(wordsInSentence[curWordInd]));
                        System.out.println(result[ind][2]);
                        break;
                    }
                }catch (NumberFormatException nfe ){
                    continue;
                }
            }
            result[ind][3]="false";
            //IMPORTED: check whether contains sentence contains imported or not
            for(int curWordInd=wordsInSentence.length-1;curWordInd>=0;curWordInd--){
                if(wordsInSentence[curWordInd].toLowerCase().matches(".*imported.*"))
                    result[ind][3]="true";
            }

        }
        return result;
    }
}
