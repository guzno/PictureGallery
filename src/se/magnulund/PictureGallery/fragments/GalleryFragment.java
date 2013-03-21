package se.magnulund.PictureGallery.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import roboguice.fragment.RoboFragment;
import se.magnulund.PictureGallery.GalleryCursorAdapter;
import se.magnulund.PictureGallery.views.GalleryImageView;
import se.magnulund.PictureGallery.R;
import se.magnulund.PictureGallery.views.StaggeredGridView;

/**
 * Created with IntelliJ IDEA.
 * User: Gustav
 * Date: 02/03/2013
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
public class GalleryFragment extends RoboFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "GalleryFragment";

    StaggeredGridView staggeredGridView;

    GalleryFragmentInterface mGalleryFragmentInterface;

    CursorAdapter mAdapter;

    public interface GalleryFragmentInterface {
        public void galleryItemClicked(int itemId, String itemPath, int itemWidth);
    }

    public static GalleryFragment newInstance() {
        GalleryFragment f = new GalleryFragment();

        return f;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(R.id.gallery_fragment_loader, null, this);

        mAdapter = new GalleryCursorAdapter(getActivity(), null, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery_fragment, container, false);

        staggeredGridView = (StaggeredGridView) view.findViewById(android.R.id.list);

        staggeredGridView.setAdapter(mAdapter);
        staggeredGridView.setOnItemClickListener(new StaggeredGridView.OnItemClickListener() {
            @Override
            public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
                GalleryImageView imageView = (GalleryImageView) view.findViewById(R.id.image_view);
                mGalleryFragmentInterface.galleryItemClicked(imageView.getImageID(), imageView.getImagePath(), imageView.getImageFullWidth());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mGalleryFragmentInterface = (GalleryFragmentInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement GalleryFragmentInterface");
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.MediaColumns.HEIGHT,
                MediaStore.MediaColumns.WIDTH
        };

        return new CursorLoader(getActivity(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}
