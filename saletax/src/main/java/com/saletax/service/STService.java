package com.saletax.service;

import com.saletax.dao.CommoditiesRepository;
import com.saletax.model.Commodities;
import com.saletax.model.STResponseObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class STService implements ApplicationContextAware {
    @Autowired
    CommoditiesRepository comRepository;

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    public STResponseObject getCommodities(String[] data) {
        InputParserNCheckCategory parser=new InputParserNCheckCategory();//Parsing Input
        String[][] cleanData=parser.cleanData(data);
        TaxCalculator calc=new TaxCalculator(cleanData);//Tax Calculator functionality
        return calc.rule(data);
    }
}
