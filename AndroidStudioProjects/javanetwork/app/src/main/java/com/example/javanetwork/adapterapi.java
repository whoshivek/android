package com.example.javanetwork;

import java.util.ArrayList;

public class adapterapi {
    private Integer total_count;
    private Boolean incomplete_results;
    private ArrayList<gitusers> items;

    public adapterapi()
    {

    }

    public adapterapi(Integer total_count, Boolean incomplete_results, ArrayList<gitusers> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<gitusers> getItems() {
        return items;
    }

    public void setItems(ArrayList<gitusers> items) {
        this.items = items;
    }
}
