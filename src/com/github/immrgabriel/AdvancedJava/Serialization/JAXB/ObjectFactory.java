package com.github.immrgabriel.AdvancedJava.Serialization.JAXB;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private final static QName QNAME = new QName(
            XMLConstants.NULL_NS_URI, "data");

    public JAXBElement<DataObject> createData(DataObject object) {
        return new JAXBElement<DataObject>(QNAME, DataObject.class, null, object);
    }
}
