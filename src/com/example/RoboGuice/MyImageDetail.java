package com.example.RoboGuice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: erikeelde
 * Date: 28/2/2013
 * Time: 01:44
 * To change this template use File | Settings | File Templates.
 */
public class MyImageDetail extends RoboFragmentActivity {

    @InjectView(R.id.image)
    ImageView image;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gallery);

        Drawable drawable = getResources().getDrawable(getIntent().getIntExtra(MyGallery.EXTRA_IMAGE_ID, 0));
        image.setImageDrawable(drawable);
    }
}