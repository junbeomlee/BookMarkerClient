package com.example.leejunbeom.bookMarker.jericho;

import net.htmlparser.jericho.Source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jun on 16. 3. 22..
 */
public class Jericho {


    //private final String libraryUrl="http://library.cau.ac.kr/search/DetailView.ax?sid=1&";
    public Source getURLtoText(String UrlString) {
        Source source = null;
        try {
            source = new Source(new URL(UrlString));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return source;
    }


}
