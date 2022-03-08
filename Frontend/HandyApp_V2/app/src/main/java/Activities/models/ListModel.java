package Activities.models;

public class ListModel {
    String category;
    String date;
    String price;
    String uid;
    String kay;
    String time;
    String imageURL;


    public ListModel(String category, String date, String price, String uid, String kay, String time, String imageURL) {
        this.category = category;
        this.date = date;
        this.price = price;
        this.uid = uid;
        this.kay = kay;
        this.time = time;
        this.imageURL = imageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKay() {
        return kay;
    }

    public void setKay(String kay) {
        this.kay = kay;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ListModel() {
    }
}
