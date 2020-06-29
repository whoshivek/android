package com.shivek.multipleview;

import java.util.List;

public class homepagemodel {
    public static final int GRID_LAYOUT =0;
    public static final int DEAL_Layout =1;

    private int type;



    //////////////////////////////
    private String title;
    private List<commonmodel> modelist ;

    public homepagemodel(int type, String title, List<commonmodel> modelist) {
        this.type = type;
        this.title = title;
        this.modelist = modelist;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<commonmodel> getModelist() {
        return modelist;
    }

    public void setModelist(List<commonmodel> modelist) {
        this.modelist = modelist;
    }

    /////////////////////////////////////////


}
