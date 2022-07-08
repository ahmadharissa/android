package com.example.post.models;

import java.util.List;

public class Post
{
    public int userId;
    public int id;
    public String title;
    public String body;

    public List<Comment> comments;

}
