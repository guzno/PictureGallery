package se.magnulund.PictureGallery.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import roboguice.fragment.RoboFragment;
import se.magnulund.PictureGallery.R;

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

    public static ImageDetailFragment newInstance(int index, int imageId) {
        ImageDetailFragment f = new ImageDetailFragment();

        Bundle args = new Bundle();
        args.putInt(INDEX, index);
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

        CursorLoader cursorLoader =

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
