package Model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gun on 28/01/2017.
 * URSS-Desktop
 */
public class Article {
    @SerializedName("feedId")
    private String feedId;
    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("description")
    private String description;
    @SerializedName("pubDate")
    private String pubDate;
    @SerializedName("author")
    private String author;
    @SerializedName("comments")
    private String comments;
    @SerializedName("enclosureUrl")
    private String enclosureUrl;
    @SerializedName("enclosureLength")
    private String enclosureLength;
    @SerializedName("enclosureType")
    private String enclosureType;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String enclosureUrl) {
        this.enclosureUrl = enclosureUrl;
    }

    public String getEnclosureLength() {
        return enclosureLength;
    }

    public void setEnclosureLength(String enclosureLength) {
        this.enclosureLength = enclosureLength;
    }

    public String getEnclosureType() {
        return enclosureType;
    }

    public void setEnclosureType(String enclosureType) {
        this.enclosureType = enclosureType;
    }

    @Override
    public String toString() {
        return "Article{" +
                "feedId='" + feedId + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", author='" + author + '\'' +
                ", comments='" + comments + '\'' +
                ", enclosureUrl='" + enclosureUrl + '\'' +
                ", enclosureLength='" + enclosureLength + '\'' +
                ", enclosureType='" + enclosureType + '\'' +
                '}';
    }
}
