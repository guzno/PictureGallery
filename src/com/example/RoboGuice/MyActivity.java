package com.example.RoboGuice;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class MyActivity extends RoboFragmentActivity implements View.OnClickListener
{
    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.thumbnail)
    ImageView thumbnail;

    @InjectView(R.id.thumbnail2)
    ImageView thumbnail2;

    @InjectResource(R.drawable.icon)
    Drawable icon;

    @InjectResource(R.string.app_name)
    String myName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        name.setText("Hello, " + myName);
        thumbnail.setImageDrawable(icon);
        thumbnail2.setImageDrawable(icon);

        thumbnail.setOnClickListener(this);
        thumbnail2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.icon);
        Bitmap bm = bitmapDrawable.getBitmap();

        Intent intent = new Intent(MyActivity.this, GalleryActivity.class);
        intent.putExtra("img",R.drawable.icon);
        Bundle scaleBundle = ActivityOptions.makeThumbnailScaleUpAnimation(view,
                bm, 0, 0).toBundle();

        startActivity(intent, scaleBundle);
    }
}
