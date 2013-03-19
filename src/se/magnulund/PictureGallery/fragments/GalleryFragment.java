package se.magnulund.PictureGallery.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
public class GalleryFragment extends RoboFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "GalleryFragment";

    public interface GalleryFragmentInterface {
        public void galleryItemClicked(int itemId);
    }

    GalleryFragmentInterface mGalleryFragmentInterface;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(R.id.gallery_fragment_loader, null, this);
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

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                MediaStore.Images.Media.DATA
        };
        return new CursorLoader(getActivity(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

}