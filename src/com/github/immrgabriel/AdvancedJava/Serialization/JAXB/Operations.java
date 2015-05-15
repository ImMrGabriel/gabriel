package com.github.immrgabriel.AdvancedJava.Serialization.JAXB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Operations {

    static final String PACKAGE = "src." + DataObject.class.getPackage().getName();

    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PACKAGE);
        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();
//
//        marshaller.marshal(dataObj, file);
//        DataObject object = (DataObject) unmarshaller.unmarshal(file);
    }
}
