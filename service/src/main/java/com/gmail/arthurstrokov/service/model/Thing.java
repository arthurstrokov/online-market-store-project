package com.gmail.arthurstrokov.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "thing", propOrder = {
        "id",
        "name",
        "description",
        "uniqueNumber",
        "price",
        "isAlive"
})
public class Thing {

    private Long id;
    private String name;
    private String description;
    private String uniqueNumber;
    private BigDecimal price;
    private Boolean isAlive;
}