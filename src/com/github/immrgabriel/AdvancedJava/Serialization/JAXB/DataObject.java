package com.github.immrgabriel.AdvancedJava.Serialization.JAXB;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "dataObj")
public class DataObject {

//    @XmlElement
    @XmlElement(required=true, name="i")
    private int i;
}
