package xyz.belvi.medium.Enums;

/**
 * Created by zone2 on 6/12/16.
 */
public enum ImageContentType {

    JPEG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif"),
    TIFF("image/tiff");

    String imageContentType;

    ImageContentType(String contentType) {
        imageContentType = contentType;
    }

    public String getContentType() {
        return imageContentType;
    }
}
