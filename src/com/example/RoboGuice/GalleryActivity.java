package com.example.RoboGuice;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import roboguice.activity.RoboActivity;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: erikeelde
 * Date: 28/2/2013
 * Time: 01:44
 * To change this template use File | Settings | File Templates.
 */
public class GalleryActivity extends RoboFragmentActivity {

    @InjectView(R.id.image)
    ImageView image;

    Drawable icon;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gallery);
        icon = getResources().getDrawable(getIntent().getIntExtra("img", 0));
        image.setImageDrawable(icon);
    }
}