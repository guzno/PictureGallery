package se.magnulund.PictureGallery;

import android.os.Bundle;
import roboguice.activity.RoboFragmentActivity;

public class MainGallery extends RoboFragmentActivity {
    private static final String TAG = "MainGallery";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gallery);
    }
}
