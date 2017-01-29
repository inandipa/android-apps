package com.example.indra.tedradiohourpodcast;

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
            if(localName.equals("item")){
                appDetails = new AppDetails();
                entry =1;
            }
            else if (localName.equals("image") && entry!=0) {
                appDetails.setImage_URL(attributes.getValue("href"));
            } else if (localName.equals("enclosure") && entry!=0) {
                appDetails.setMP3_URL(attributes.getValue("url"));
            }


        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals("item")) {
                appDetailsList.add(appDetails);
                i++;
            }else if( localName.equals("title") && entry!=0){
                appDetails.setTitle(sb.toString().trim());
            }else if( localName.equals("description") && entry!=0){
                appDetails.setDescription(sb.toString().trim());
            }else if( localName.equals("pubDate") && entry!=0){

                String string =sb.toString().trim();
                String[] parts = string.split(" ");
                string="";
                for(i =0 ; i<4;i++){
                    string = string.concat(parts[i]);
                    string = string.concat(" ");
                }
                appDetails.setPublication_date(string);
            }else if( localName.equals("duration") && entry!=0 ) {

                appDetails.setDuration(sb.toString().trim());
                Log.d("duration",appDetails.getDuration());
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

