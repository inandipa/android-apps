package com.example.indra.appsactivity;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by indra on 6/14/16.
 */public class AppDetailsUtill {
    public static class ParseAppDetails extends DefaultHandler {
       ArrayList<AppDetails> appDetailsList;
        AppDetails appDetails;
        int i=0;
        int entry = 0;
        StringBuilder sb;
        int image = 0;

        public static ArrayList<AppDetails> ParseXml(InputStream in) throws IOException, SAXException {
            ParseAppDetails parse = new ParseAppDetails();
            Xml.parse(in, Xml.Encoding.UTF_8,parse);
            Log.d("demo","sent for parsing");
            return parse.getAppDetailsList();
        }

        public ArrayList<AppDetails> getAppDetailsList() {
            return appDetailsList;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("entry")){
                appDetails = new AppDetails();
                entry =1;
            }

            else if (localName.equals("id") && entry!=0) {
                appDetails.setId(attributes.getValue("id"));
            }else if (localName.equals("price") && entry!=0) {
                appDetails.setApp_price(attributes.getValue("amount"));
            }else if (localName.equals("releaseDate") && entry!=0) {
                appDetails.setRelease_date(attributes.getValue("label"));
            }else if (localName.equals("image") && entry!=0) {
                image =Integer.parseInt(attributes.getValue("height"));
            } else if (localName.equals("category") && entry!=0) {
                appDetails.setCategory(attributes.getValue("term"));
            }


        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals("entry")) {
                appDetailsList.add(appDetails);
                i++;
            }else if( localName.equals("name") && entry!=0){
                appDetails.setApp_Title(sb.toString().trim());
            }else if( localName.equals("artist") && entry!=0){
                appDetails.setDeveloper_name(sb.toString().trim());
            }else if( localName.equals("id") && entry!=0){
                appDetails.setUri(sb.toString().trim());
            }else if( localName.equals("image") && entry!=0 &&(image==75 )) {
                appDetails.setLarge_photo_url(sb.toString().trim());
            }else if( localName.equals("image") && entry!=0 &&(image==100)) {
                appDetails.setSmall_photo_url(sb.toString().trim());
            }
            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            sb =new StringBuilder();
            appDetailsList = new ArrayList<>();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }



    }

}

