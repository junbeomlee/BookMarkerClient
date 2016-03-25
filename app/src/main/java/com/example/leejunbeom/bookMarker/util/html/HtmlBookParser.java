package com.example.leejunbeom.bookMarker.util.html;

import com.example.leejunbeom.bookMarker.model.Book;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jun on 16. 3. 22..
 */
public class HtmlBookParser implements HtmlParser{
    public HtmlBookParser() {
    }

    @Override
    public Object sourceToObject(Source htmltoString) {

        Element metaDataBodyInfoList= htmltoString.getElementById("metaDataBody");
        List<Element> ElmentList=metaDataBodyInfoList.getAllElements(HTMLElementName.TR);
        Book book = new Book();
        for (Element elemet:ElmentList) {
            Segment bookAttribute=elemet.getAllElements().get(1).getContent();
            Segment bookAttributeValue=ElmentList.get(0).getAllElements().get(2).getContent();
            String bookAttributeValueString=bookAttributeValue.toString().replaceAll("\\s+", "");
            this.setBookAttribute(book,bookAttribute.toString().replaceAll("\\s+",""),bookAttributeValueString);
        }
        ///개인 저자 , 주제명 제외
        Segment dataType=ElmentList.get(0).getAllElements().get(2).getContent();
        String dataTypeString=dataType.toString().replaceAll("\\s+", "");

        Segment titelAuthors=ElmentList.get(1).getAllElements().get(2).getContent();
        String titelAuthorsTypeString=titelAuthors.toString().replaceAll("\\s+", "");

        Segment parallelTitle=ElmentList.get(2).getAllElements().get(2).getContent();
        String parallelTitleString=parallelTitle.toString().replaceAll("\\s+", "");

        Segment editionStatement=ElmentList.get(4).getAllElements().get(2).getContent();
        String editionStatementString=editionStatement.toString().replaceAll("\\s+", "");

        Segment formMatters=ElmentList.get(5).getAllElements().get(2).getContent();
        String formMattersString=formMatters.toString().replaceAll("\\s+", "");

        Segment publicationMatters=ElmentList.get(6).getAllElements().get(2).getContent();
        String publicationMatterString=publicationMatters.toString().replaceAll("\\s+", "");

        Segment generalAspects=ElmentList.get(7).getAllElements().get(2).getContent();
        String generalAspectsString=generalAspects.toString().replaceAll("\\s+","");

        Segment ISBN=ElmentList.get(9).getAllElements().get(2).getContent();
        String ISBNString=ISBN.toString().replaceAll("\\s+","");

        Segment symbolicRequest=ElmentList.get(10).getAllElements().get(2).getContent();
        String symbolicRequestString=symbolicRequest.toString().replaceAll("\\s+","");

        //System.out.print(ElmentList.get(0).toString());
        return new Book(dataTypeString,titelAuthorsTypeString,editionStatementString,formMattersString,publicationMatterString,generalAspectsString
                        ,ISBNString,symbolicRequestString);
    }

    public void setBookAttribute(Book book, String attribute, String bookAttributeValueString){

        switch (attribute){
            case "자료유형:":
                book.setDataType(bookAttributeValueString);
                break;
            case "서명/저자:":
                book.setTitileAuthorsType(bookAttributeValueString);
                break;
            case "판사항:":
                book.setEditionStateMent(bookAttributeValueString);
                break;
            case "발행사항:":
                book.setPublicationMatter(bookAttributeValueString);
                break;
            case "일반사항:":
                book.setGeneralAspects(bookAttributeValueString);
                break;
            case "ISBN:":
                book.setIsbn(bookAttributeValueString);
                break;
            case "청구기호:":
                book.setSymbolicRequest(bookAttributeValueString);
                break;
            default:
                break;
        }

    }
}
