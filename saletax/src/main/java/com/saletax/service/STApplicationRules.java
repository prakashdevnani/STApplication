package com.saletax.service;

import com.saletax.model.STResponseObject;

//All Rules Declarations
public interface STApplicationRules {
    public STResponseObject rule(String[] data);
}
