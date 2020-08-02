package com.saletax.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
    H2 Database Entity for initial values of item and their category db
*/

@NoArgsConstructor
@Entity
@Table(name="Commodities")
public class Commodities {
    @Getter @Setter @Id private long id;
    @Getter @Setter private String category;
    @Getter @Setter private String item;
}
