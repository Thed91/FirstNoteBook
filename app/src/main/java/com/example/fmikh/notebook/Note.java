package com.example.fmikh.notebook;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fmikh on 28.01.2018.
 */

public class Note implements Serializable,Comparable<Note> {

    private static final long serialVersionUID = -2299835551018408482L;

    private String name;
    private String content;
    private Date date;
    private Integer number;

    public Note() {
    }

    public Note(String name, String content, Date date) {
        super();
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        return number != null ? number.equals(note.number) : note.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Note note) {
        return note.getDate().compareTo(date);
    }
}
