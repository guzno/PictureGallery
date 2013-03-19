package se.magnulund.PictureGallery.fragments;

import android.app.Activity;
import android.os.Bundle;
import roboguice.fragment.RoboFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Gustav
 * Date: 02/03/2013
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
public class GalleryFragment extends RoboFragment {
    private static final String TAG = "GalleryFragment";

    public interface GalleryFragmentInterface {
        public void galleryItemClicked(int itemId);
    }

    GalleryFragmentInterface mGalleryFragmentInterface;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);    //To change body of overridden methods use File | Settings | File Templates.
        try {
            mGalleryFragmentInterface = (GalleryFragmentInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement GalleryFragmentInterface");
        }
    }
}