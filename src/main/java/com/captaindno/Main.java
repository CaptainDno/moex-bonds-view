package com.captaindno;

import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, XMLStreamException, XMLParseException {
        App app = new App();
        app.run();
    }
}