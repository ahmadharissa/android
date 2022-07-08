package com.example.json.models;

public class Post
{
    public int userId;
    public int id;
    public String title;
    public String body;

    public void setBody(String body){
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
