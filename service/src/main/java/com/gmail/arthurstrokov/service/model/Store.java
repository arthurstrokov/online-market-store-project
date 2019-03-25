package com.gmail.arthurstrokov.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor // Only for parsing items
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "store")
public class Store {

    @XmlElement(name = "thing")
    private List<Thing> things = new ArrayList<>();
}