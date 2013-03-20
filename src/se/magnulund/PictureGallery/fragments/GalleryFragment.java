package se.magnulund.PictureGallery.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

    ListView listView;

    GalleryFragmentInterface mGalleryFragmentInterface;

    SimpleCursorAdapter mAdapter;

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

        getLoaderManager().initLoader(R.id.gallery_fragment_loader, null, this);

        mAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_list_item_2,
                null,
                new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery_fragment, container, false);

        listView = (ListView) view.findViewById(android.R.id.list);

        listView.setAdapter(mAdapter);
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
                MediaStore.Images.Media.DATA
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
