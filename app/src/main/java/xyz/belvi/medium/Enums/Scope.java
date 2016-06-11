package xyz.belvi.medium.Enums;

/**
 * Created by zone2 on 6/10/16.
 */
public enum Scope {
    BASIC("basicProfile", false),
    PUBLICATION("listPublications", false),
    IMAGE_UPLOAD("uploadImage", true),
    POST("publishPost", false);

    public String scopeName;
    public boolean isScopeExtended;

    Scope(String scope, boolean isExtended) {
        this.scopeName = scope;
        this.isScopeExtended = isExtended;
    }

    public String getScopeName() {
        return this.scopeName;
    }

    public boolean isScopeExtended() {
        return this.isScopeExtended;
    }
}
