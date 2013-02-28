package com.example.RoboGuice;

import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class MyActivity extends RoboActivity {
    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.thumbnail)
    ImageView thumbnail;

    @InjectResource(R.drawable.icon)
    Drawable icon;

    @InjectResource(R.string.app_name)
    String myName;

    @Inject
    LocationManager loc;

    @Inject
    LayoutInflater inflater;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        name.setText("Hello, " + myName);
        thumbnail.setImageDrawable(icon);

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
