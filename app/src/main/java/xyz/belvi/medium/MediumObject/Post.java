package xyz.belvi.medium.MediumObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;

import xyz.belvi.medium.Enums.ContentFormat;
import xyz.belvi.medium.Enums.EnumUtils;
import xyz.belvi.medium.Enums.PublishStatus;

/**
 * Created by zone2 on 6/10/16.
 */
public class Post {
    String title, content, canonicalUrl, authorId, licenseUrl, id, url;
    ContentFormat contentFormat;
    License license;
    long publishedAt;
    PublishStatus publishStatus;
    HashSet<String> tags = new HashSet<>();


    public Post() {

    }

    public Post(String responseObj) {
        try {
            JSONObject responseObject = new JSONObject(responseObj);
            JSONObject postDataObject = responseObject.getJSONObject("data");
            setId(postDataObject.optString("id"));
            setTitle(postDataObject.optString("title"));
            setAuthorId(postDataObject.optString("authorId"));
            JSONArray tags = postDataObject.getJSONArray("tags");
            for (int index = 0; index < tags.length(); index++) {
                addTag(tags.getString(index));
            }
            setUrl(postDataObject.optString("url"));
            setCanonicalUrl(postDataObject.optString("canonicalUrl"));
            setPublishStatus(PublishStatus.valueOf(postDataObject.optString("publishStatus").toUpperCase()));
            setPublishedAt(postDataObject.optLong("publishedAt"));
            setLicense(EnumUtils.getLicenseByType(postDataObject.optString("license")));
            setLicenseUrl(postDataObject.optString("licenseUrl"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Post(String title, String content, String canonicalUrl, PublishStatus publishStatus, License license, ContentFormat contentFormat, String... tags) {
        setTitle(title);
        setContent(content);
        setCanonicalUrl(canonicalUrl);
        setPublishStatus(publishStatus);
        setLicense(license);
        setContentFormat(contentFormat);
        for (String tag : tags) {
            addTag(tag);
        }

    }

    public Post(String title, String content, PublishStatus publishStatus, ContentFormat contentFormat, String... tags) {
        this(title, content, "", publishStatus, License.ALL_RIGHT_RESERVED, contentFormat, tags);
    }

    public Post(String title, String content, String canonicalUrl, PublishStatus publishStatus, ContentFormat contentFormat, String... tags) {
        this(title, content, canonicalUrl, publishStatus, License.ALL_RIGHT_RESERVED, contentFormat, tags);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
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

    public ContentFormat getContentFormat() {
        return contentFormat;
    }

    public void setContentFormat(ContentFormat contentFormat) {
        this.contentFormat = contentFormat;
    }

    public long getPublishedAt() {
        return this.publishedAt;
    }

    public void setPublishedAt(long publishedAt) {
        this.publishedAt = publishedAt;
    }

    public PublishStatus getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(PublishStatus publishStatus) {
        this.publishStatus = publishStatus;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }
}
