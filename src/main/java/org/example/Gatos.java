package org.example;

public class Gatos {

    public String id;
    public String url;
    public String apiKey = "live_cyGpkMJPPHKgQiDvhhGcogkYO8cvz4SSV5x6h5TKP7LaVKLG1joc44MNDwL2Fdal";
    public String image;

    public Gatos() {
    }

    public Gatos(String id, String url, String apiKey, String image) {
        this.id = id;
        this.url = url;
        this.apiKey = apiKey;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
