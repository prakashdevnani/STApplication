package com.saletax.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
    /*
        Format of Response
        */
@NoArgsConstructor
public class STResponseObject {
    @Getter @Setter String[] data;
    @Getter @Setter float saleTax=0.0f;
    @Getter @Setter float totalBill=0.0f;
}
