package com.example.leejunbeom.test;

import com.example.leejunbeom.bookMarker.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.util.html.HtmlBookParser;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;

import net.htmlparser.jericho.Source;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.*;

/**
 * Created by Jun on 16. 3. 22..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class jerichoTest {

    Jericho jericho;
    HtmlParser htmLBookparser;
    @Before
    public void setUp(){
        jericho=new Jericho();
        htmLBookparser=new HtmlBookParser();
    }

    @After
    public void tearDown(){


    }

    @Test
    public void should_htmlparseintobookobject_test(){
        Source htmltoString = jericho.getURLtoText("http://library.cau.ac.kr/search/DetailView.ax?sid=1&cid=5241729");
        assertNotNull("htmlToString are null", htmltoString);
        Book book = (Book)htmLBookparser.sourceToObject(htmltoString);
        assertEquals("book are equal","Book{dataType='국내서단행본', titileAuthorsType='양안시와사시/진가헌,최혜정,이준범편저', editionStateMent='개정3판', formMatters='서울:대학서림,2011', publicationMatter='323p.:삽화;26cm', generalAspects='색인수록<br/>', isbn='9788980168866', symbolicRequest='617.762진가헌양3'}",book.toString());
    }

}
