package xyz.belvi.medium.Callback;

import java.util.ArrayList;

import xyz.belvi.medium.MediumObject.Contributor;
import xyz.belvi.medium.MediumObject.Publication;

/**
 * Created by zone2 on 6/10/16.
 */
public interface PublicationCallback extends ConnectionCallback {

    void onPublicationRetrieved(ArrayList<Publication> publications);
    void onReceivedContributors(ArrayList<Contributor> contributors);

}
