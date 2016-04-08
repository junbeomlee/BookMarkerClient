package com.example.leejunbeom.bookMarker.model;

/**
 * Created by Jun on 16. 3. 21..
 */

public class Book {

    private String dataType;
    private String titileAuthorsType;
    private String editionStateMent;
    private String formMatters;
    private String publicationMatter;
    private String generalAspects;
    private String isbn;
    private String symbolicRequest;

    public Book(){

    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setTitileAuthorsType(String titileAuthorsType) {
        this.titileAuthorsType = titileAuthorsType;
    }

    public void setEditionStateMent(String editionStateMent) {
        this.editionStateMent = editionStateMent;
    }

    public void setFormMatters(String formMatters) {
        this.formMatters = formMatters;
    }

    public void setPublicationMatter(String publicationMatter) {
        this.publicationMatter = publicationMatter;
    }

    public void setGeneralAspects(String generalAspects) {
        this.generalAspects = generalAspects;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setSymbolicRequest(String symbolicRequest) {
        this.symbolicRequest = symbolicRequest;
    }

    public Book(String dataType, String titileAuthorsType, String editionStateMent, String formMatters, String publicationMatter, String generalAspects, String isbn, String symbolicRequest) {
        this.dataType = dataType;
        this.titileAuthorsType = titileAuthorsType;
        this.editionStateMent = editionStateMent;
        this.formMatters = formMatters;
        this.publicationMatter = publicationMatter;
        this.generalAspects = generalAspects;
        this.isbn = isbn;
        this.symbolicRequest = symbolicRequest;
    }

    @Override
    public String toString() {
        return "Book{" +
                "dataType='" + dataType + '\'' +
                ", titileAuthorsType='" + titileAuthorsType + '\'' +
                ", editionStateMent='" + editionStateMent + '\'' +
                ", formMatters='" + formMatters + '\'' +
                ", publicationMatter='" + publicationMatter + '\'' +
                ", generalAspects='" + generalAspects + '\'' +
                ", isbn='" + isbn + '\'' +
                ", symbolicRequest='" + symbolicRequest + '\'' +
                '}';
    }

    public String getDataType() {
        return dataType;
    }

    public String getTitileAuthorsType() {
        return titileAuthorsType;
    }

    public String getEditionStateMent() {
        return editionStateMent;
    }

    public String getFormMatters() {
        return formMatters;
    }

    public String getPublicationMatter() {
        return publicationMatter;
    }

    public String getGeneralAspects() {
        return generalAspects;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getSymbolicRequest() {
        return symbolicRequest;
    }
}
