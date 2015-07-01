package com.example.bacons.newbnotepad.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by mbagliojr on 6/30/15.
 */
public class Note extends SugarRecord<Note> {

    private Date createdDate;
    private String title;
    private String content;

    public Note() {
        createdDate = new Date();
        title = "";
        content = "";
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
