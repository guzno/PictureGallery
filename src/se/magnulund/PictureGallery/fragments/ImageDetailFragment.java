package se.magnulund.PictureGallery.fragments;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import roboguice.fragment.RoboFragment;
import se.magnulund.PictureGallery.R;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Gustav
 * Date: 02/03/2013
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class ImageDetailFragment extends RoboFragment {
    private static final String TAG = "ImageDetailFragment";

    private static final String INDEX = "index";
    public static final String IMAGE_ID = "image_id";

    public static ImageDetailFragment newInstance(int imageId) {
        ImageDetailFragment f = new ImageDetailFragment();

        Bundle args = new Bundle();
        args.putInt(IMAGE_ID, imageId);
        f.setArguments(args);

        return f;
    }

    public int getImageId() {
        return getArguments().getInt(IMAGE_ID);
    }

    private void setImageId(int imageId) {
        getArguments().putInt(IMAGE_ID, imageId);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.image_detail_fragment, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.ImageDetailView);

        String imageId = Integer.toString(getArguments().getInt(IMAGE_ID));

        Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageId);

        Bitmap bitmap;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            imageView.setImageBitmap(bitmap);

        } catch (IOException e) {
            Log.e(TAG, "File not found: " + e.toString(), e);
        }


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
