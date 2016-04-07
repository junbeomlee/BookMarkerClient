package com.example.leejunbeom.bookMarker.network.jericho;

import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;

import net.htmlparser.jericho.Source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

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
