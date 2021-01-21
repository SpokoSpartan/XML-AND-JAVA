package com.spot.on.demo.models;

import java.util.List;

public class Root{
    private Metadata metadata;
    private List<Item> items;
    private ApiInfo api_info;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ApiInfo getApi_info() {
        return api_info;
    }

    public void setApi_info(ApiInfo api_info) {
        this.api_info = api_info;
    }
}
