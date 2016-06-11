package xyz.belvi.medium.Callback;

import xyz.belvi.medium.MediumObject.MediumImage;
import xyz.belvi.medium.MediumObject.Post;

/**
 * Created by zone2 on 6/10/16.
 */
public interface MediumPostPublicationCallback extends ConnectionCallback {

    void PostPublished(Post post);

    void ImageUploaded(MediumImage mediumImage);
}
