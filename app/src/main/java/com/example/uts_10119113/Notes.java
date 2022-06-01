package com.example.uts_10119113;

import java.util.Date;

public class Notes {
    private String id, title, body, category;
    private Date created_at;

    public Notes(String id, String title, String body, String category, Date created_at) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.category = category;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
