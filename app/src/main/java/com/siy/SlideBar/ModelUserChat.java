package com.siy.SlideBar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo on 3/15/2018.
 */

public class ModelUserChat {
    private CircleImageView ivProfilepic;
    private String name;
    private String message;
    private String Time;

    public ModelUserChat(CircleImageView ivProfilepic, String name, String message, String time) {
        this.ivProfilepic = ivProfilepic;
        this.name = name;
        this.message = message;
        Time = time;
    }

    public CircleImageView getIvProfilepic() {
        return ivProfilepic;
    }

    public void setIvProfilepic(CircleImageView ivProfilepic) {
        this.ivProfilepic = ivProfilepic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


  /*  public String getURLForResource(int resourceId) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();*/
}

