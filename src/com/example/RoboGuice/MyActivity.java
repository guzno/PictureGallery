package com.example.RoboGuice;

import android.content.Intent;
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

public class MyActivity extends RoboFragmentActivity {
    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.thumbnail)
    ImageView thumbnail;

    @InjectResource(R.drawable.icon)
    Drawable icon;

    @InjectResource(R.string.app_name)
    String myName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        name.setText("Hello, " + myName);
        thumbnail.setImageDrawable(icon);

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });
    }
}
