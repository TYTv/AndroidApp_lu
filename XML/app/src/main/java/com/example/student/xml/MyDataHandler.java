package com.example.student.xml;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by student on 2017/10/11.
 */

public class MyDataHandler extends DefaultHandler {

    boolean isItem = false;
    boolean isTitle = false;
    boolean isLink = false;
    public ArrayList<objRSS> dataRSS = new ArrayList<>();
    objRSS rss;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (qName.equals("item")) {
            isItem = true;
            rss = new objRSS();
        }
        if (qName.equals("title")) {
            isTitle = true;
        }
        if (qName.equals("link")) {
            isLink = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (qName.equals("link")) {
            isLink = false;
        }
        if (qName.equals("title")) {
            isTitle = false;
        }
        if (qName.equals("item")) {
            isItem = false;
            dataRSS.add(rss);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (isTitle && isItem) {
            rss.title = new String(ch, start, length);
//            Log.d("NET", new String(ch, start, length));
        }

        if (isLink && isItem) {
            rss.link = new String(ch, start, length);
        }

    }
}
