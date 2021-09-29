package com.myf.booktbm;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table
public class Book {
    @Id
    @Column(name = "bookId")
    private int bookId;

    @Column(name = "bookName")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "bookStatus")
    private String bookStatus;

    @Column(name = "theLastChapter")
    private String theLastChapter;

    @Column(name = "modifiedStatus")
    private String modifiedStatus;

    @Column(name = "modifiedTime")
    private Date modifiedTime;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "txtUrl")
    private String txtUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTxtUrl() {
        return txtUrl;
    }

    public void setTxtUrl(String txtUrl) {
        this.txtUrl = txtUrl;
    }

    public String getTheLastChapter() {
        return theLastChapter;
    }

    public void setTheLastChapter(String theLastChapter) {
        this.theLastChapter = theLastChapter;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getModifiedStatus() {
        return modifiedStatus;
    }

    public void setModifiedStatus(String modifiedStatus) {
        this.modifiedStatus = modifiedStatus;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", bookStatus='" + bookStatus + '\'' +
                ", theLastChapter='" + theLastChapter + '\'' +
                ", modifiedStatus='" + modifiedStatus + '\'' +
                ", modifiedTime=" + modifiedTime +
                ", imageUrl='" + imageUrl + '\'' +
                ", txtUrl='" + txtUrl + '\'' +
                '}';
    }
}
