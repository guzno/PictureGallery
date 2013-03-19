package se.magnulund.PictureGallery.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import roboguice.fragment.RoboFragment;
import se.magnulund.PictureGallery.R;

/**
 * Created with IntelliJ IDEA.
 * User: Gustav
 * Date: 02/03/2013
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
public class GalleryFragment extends RoboFragment {

    private static final String TAG = "GalleryFragment";

    GalleryFragmentInterface mGalleryFragmentInterface;

    public interface GalleryFragmentInterface {
        public void galleryItemClicked(int itemId);
    }

    public static GalleryFragment newInstance(int index) {
        GalleryFragment f = new GalleryFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery_fragment, container, false);
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