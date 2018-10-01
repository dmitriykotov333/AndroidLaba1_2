package com.onpu.statements;

/**
 * Created by Admin on 21.09.2018.
 */

public class Item {
    private String id;
    private String name;
    private String desc;
    private String comments;
    private Integer icon;

    Item() {

    }

    Item(String id, String name, String desc, String comments, Integer icon) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.comments = comments;
        this.icon = icon;
    }

    Item(String name, String desc, String comments) {
        this.name = name;
        this.desc = desc;
        this.comments = comments;
    }
    Item(String name, String desc, String comments, Integer icon) {
        this.name = name;
        this.desc = desc;
        this.comments = comments;
        this.icon = icon;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}
