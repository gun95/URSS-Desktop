package Model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by gun on 28/01/2017.
 * URSS-Desktop
 */
public class Feed {
    @SerializedName("id")
    private String feedId;
    @SerializedName("url")
    private String url;
    @SerializedName("articles")
    private ArrayList<String> articleIdArrayList = new ArrayList<String>();

    private ArrayList<Article> articleArrayList = new ArrayList<Article>();

    public Feed() {
    }

    public Feed(String feedId) {
        this.feedId = feedId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public ArrayList<String> getArticleIdArrayList() {
        return articleIdArrayList;
    }

    public void setArticleIdArrayList(ArrayList<String> articleIdArrayList) {
        this.articleIdArrayList = articleIdArrayList;
    }

    public ArrayList<Article> getArticleArrayList() {
        return articleArrayList;
    }

    public void setArticleArrayList(ArrayList<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    public void addArticleArrayList(Article article)
    {
        this.articleArrayList.add(article);
    }

    @Override
    public String toString() {
        return "Feed{" +
                "feedId='" + feedId + '\'' +
                ", url='" + url + '\'' +
                ", articleIdArrayList=" + articleIdArrayList +
                ", articleArrayList=" + articleArrayList +
                '}';
    }
}
