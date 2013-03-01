package com.example.RoboGuice;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class MyGallery extends RoboFragmentActivity implements View.OnClickListener
{
    private static final String TAG = "MyGallery";

    @InjectView(R.id.thumbnail)
    ImageView thumbnail;

    @InjectView(R.id.thumbnail2)
    ImageView thumbnail2;

    @InjectView(R.id.thumbnail3)
    ImageView thumbnail3;

    @InjectResource(R.drawable.thumbnail)
    Drawable icon;

    public static final int IMAGE_ID = 0;

    public static final String EXTRA_IMAGE_ID = "image_id";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        thumbnail.setImageDrawable(getResources().getDrawable(R.drawable.icon));
        thumbnail.setTag(R.drawable.icon);
        thumbnail.setOnClickListener(this);

        thumbnail2.setImageDrawable(getResources().getDrawable(R.drawable.icon));
        thumbnail2.setTag(R.drawable.icon);
        thumbnail2.setOnClickListener(this);

        thumbnail3.setImageDrawable(getResources().getDrawable(R.drawable.icon));
        thumbnail3.setTag(R.drawable.icon);
        thumbnail3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ImageView imageView = (ImageView) view;

        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bm = drawable.getBitmap();

        Bundle scaleBundle = ActivityOptions.makeThumbnailScaleUpAnimation(
                imageView, bm, 0, 0).toBundle();

        Intent subActivity = new Intent(MyGallery.this, MyImageDetail.class);

        int resID = (Integer) imageView.getTag();
        subActivity.putExtra(EXTRA_IMAGE_ID, resID);

        startActivity(subActivity, scaleBundle);
    }
}
