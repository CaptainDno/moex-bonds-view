package com.captaindno;

import com.captaindno.types.Bond;
import com.captaindno.types.YieldInfo;
import com.ctc.wstx.stax.WstxInputFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class Parser {
    public static HashMap<String, Bond> parseFromURI(URL url) throws XMLParseException, IOException, XMLStreamException {
        XMLInputFactory inputFactory = new WstxInputFactory();
        XMLStreamReader sr = inputFactory.createXMLStreamReader(new BufferedInputStream(url.openStream()));
        XmlMapper mapper = new XmlMapper(inputFactory);

        HashMap<String, Bond> bonds = new HashMap<>();

        while (!sr.hasName() || !sr.getName().toString().equals("data")) sr.next();

        if (!sr.getAttributeValue(0).equals("securities")) {
            throw new XMLParseException("Unexpected format");
        }

        while (!sr.hasName() || !sr.getName().toString().equals("rows")) sr.next();

        sr.next();
        // Load all bonds
        int cnt = 0;
        while (!sr.isEndElement()) {
            Bond bond = mapper.readValue(sr, Bond.class);
            //System.out.println(cnt++);
            bonds.putIfAbsent(bond.getID(), bond);
            sr.next();
            sr.next();
        }

        while (!sr.hasName() || !(sr.isStartElement() && sr.getName().toString().equals("data") && sr.getAttributeValue(0).equals("marketdata_yields"))) {
            sr.next();
        }
        while (!sr.hasName() || !sr.getName().toString().equals("rows")) sr.next();

        sr.next(); sr.next();

        while (!sr.isEndElement()) {
            String id = sr.getAttributeValue(0);

            YieldInfo info = mapper.readValue(sr, YieldInfo.class);
            Bond bond = bonds.get(id);
            if (bond != null) {
                bond.updateYieldInfo(info);
            }
            sr.next(); sr.next();
        }

        sr.close();
        return bonds;
    }
}
