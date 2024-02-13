package org.example;

public class GatosFav {

    String id;
    String image_id;
    String apiKey = "live_cyGpkMJPPHKgQiDvhhGcogkYO8cvz4SSV5x6h5TKP7LaVKLG1joc44MNDwL2Fdal";
    ImageX image;

    public GatosFav() {
    }

    public GatosFav(String id, String image_id, String apiKey, ImageX image) {
        this.id = id;
        this.image_id = image_id;
        this.apiKey = apiKey;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ImageX getImage() {
        return image;
    }

    public void setImage(ImageX image) {
        this.image = image;
    }
}
