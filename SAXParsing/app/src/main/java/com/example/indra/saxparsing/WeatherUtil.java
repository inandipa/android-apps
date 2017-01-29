package com.example.indra.saxparsing;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by indra on 6/12/16.
 */
public class WeatherUtil {
    public static class WeatherParse extends DefaultHandler{
        Weather w ;
        ArrayList<String> list;
        StringBuilder sb;
        public Weather getW() {
            return w;
        }

        public static Weather ParseWeather(InputStream in ) throws IOException, SAXException {
            WeatherParse parse = new WeatherParse();
            Xml.parse(in, Xml.Encoding.UTF_8,parse);
           return parse.getW() ;
        }


        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            w = new Weather();
            sb = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("name")){
                w.setName(sb.toString());
            }
            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }
    }
}
