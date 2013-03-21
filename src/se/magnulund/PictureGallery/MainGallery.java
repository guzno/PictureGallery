package se.magnulund.PictureGallery;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import roboguice.activity.RoboFragmentActivity;
import se.magnulund.PictureGallery.fragments.GalleryFragment;
import se.magnulund.PictureGallery.fragments.ImageDetailFragment;

public class MainGallery extends RoboFragmentActivity implements GalleryFragment.GalleryFragmentInterface {
    private static final String TAG = "MainGallery";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gallery);

        getLoaderManager();
        getSupportLoaderManager();

        if (savedInstanceState == null) {
            GalleryFragment galleryFragment = GalleryFragment.newInstance();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.add(R.id.galleryfragment, galleryFragment);

            fragmentTransaction.commit();
        }
    }

    @Override
    public void galleryItemClicked(int itemId) {
        ImageDetailFragment imageDetailFragment = ImageDetailFragment.newInstance(itemId);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.galleryfragment, imageDetailFragment);
        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

    }
}
