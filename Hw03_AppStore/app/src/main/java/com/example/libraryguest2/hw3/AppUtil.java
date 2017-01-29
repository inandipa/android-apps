package com.example.libraryguest2.hw3;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Siddharth on 6/12/2016.
 */
public class AppUtil {

    static public class AppPullParser{
        static ArrayList<App> parseApp(InputStream in)
        {
            try {
                XmlPullParser xmlParse= XmlPullParserFactory.newInstance().newPullParser();
                xmlParse.setInput(in,"UTF-8");
                App app = null;
                final ArrayList<App> appList=new ArrayList<App>();
                int event=xmlParse.getEventType();
                while(event!=XmlPullParser.END_DOCUMENT)
                {

                    switch(event){
                        case XmlPullParser.START_TAG:
                            if(xmlParse.getName().equals("title"))
                            {

                                app.setTitle(xmlParse.nextText());

                            }
                            else if(xmlParse.getName().equals("im:artist"))
                            {
                                app.setName(xmlParse.nextText());
                            }
                            else if(xmlParse.getName().equals("im:releaseDate"))
                            {
                                app.setDate(xmlParse.getAttributeValue(null,"label"));
                            }
                           if(xmlParse.getName().equals("im:price"))
                            {
                                app.setPrice(Double.parseDouble(xmlParse.getAttributeValue(null,"amount")));
                            }
                            if(xmlParse.getName().equals("im:image"))
                            {
                                if(xmlParse.getAttributeValue(null,"height").equals("53")) {
                                    Log.d("small","small");
                                    app.setIconSmall(xmlParse.nextText());
                                }
                                else if(xmlParse.getAttributeValue(null,"height").equals("100")) {
                                    Log.d("large","large");
                                    app.setIcon(xmlParse.nextText());
                                }
                            }
                            if(xmlParse.getName().equals("id"))
                            {
                                app=new App();
                                app.setUri(xmlParse.nextText());
                            }


                            break;
                        case XmlPullParser.END_TAG:
                            if(xmlParse.getName().equals("entry"))
                            {
                                appList.add(app);
                                app=null;
                            }
                            break;
                    }


                   event=xmlParse.next();
                }
                return appList;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
