package xyz.belvi.medium.Enums;

import xyz.belvi.medium.Network.NetworkConstant;

/**
 * Created by zone2 on 6/10/16.
 */
public enum ApiHost {
    REFRESH_TOKEN(false, "https://api.medium.com/v1/tokens", NetworkConstant.POST, "refresh_token", "application/x-www-form-urlencoded"),
    ME(true, "https://api.medium.com/v1/me", NetworkConstant.GET, "application/json"),
    PUBLICATION(true, "https://api.medium.com/v1/users/", NetworkConstant.GET, "application/json"),
    CONTRIBUTION(true, "https://api.medium.com/v1/publications/", NetworkConstant.GET, ""),
    POST(true, "https://api.medium.com/v1/users/", NetworkConstant.POST, "application/json"),
    PUBLICATION_POST(true, "https://api.medium.com/v1/publications/", NetworkConstant.POST, "application/json"),
    IMAGE_UPLOAD(true, "https://api.medium.com/v1/images", NetworkConstant.POST, "multipart/form-data; boundary=FormBoundaryXYZ", "", ImageContentType.PNG),
    ACCESS_TOKEN(false, "https://api.medium.com/v1/tokens?", NetworkConstant.POST, "authorization_code", "application/x-www-form-urlencoded"),
    REQUEST_CODE(false, "https://medium.com/m/oauth/authorize?", NetworkConstant.GET, null);

    String uriPath, action, grantType, contentType, filePath;
    boolean hasBearer;
    ImageContentType imageContentType;


    ApiHost(boolean hasBearer, String path, String action, String contentType) {
        uriPath = path;
        this.action = action;
        this.contentType = contentType;
        this.hasBearer = hasBearer;

    }


    ApiHost(boolean hasBearer, String path, String action, String grantType, String contentType) {
        uriPath = path;
        this.action = action;
        this.grantType = grantType;
        this.contentType = contentType;
        this.hasBearer = hasBearer;

    }

    ApiHost(boolean hasBearer, String path, String action, String contentType, String filePath, ImageContentType imageContentType) {
        uriPath = path;
        this.action = action;
        this.contentType = contentType;
        this.hasBearer = hasBearer;
        this.filePath = filePath;
        this.imageContentType = imageContentType;

    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ImageContentType getImageContentType() {
        return this.imageContentType;
    }

    public void setImageContentType(ImageContentType imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getContentType() {
        return this.contentType;
    }

    public boolean hasBearer() {
        return this.hasBearer;
    }

    public String getUriPath() {
        return this.uriPath;
    }

    public String getAction() {
        return this.action;
    }

    public String getGrantType() {
        return this.grantType;
    }
}
