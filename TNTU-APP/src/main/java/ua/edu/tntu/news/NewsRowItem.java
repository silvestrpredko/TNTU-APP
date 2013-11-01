package ua.edu.tntu.news;

public class NewsRowItem {
    private int imageId;
    private String title;

    public NewsRowItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}