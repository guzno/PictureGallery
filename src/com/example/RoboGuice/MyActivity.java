package com.example.RoboGuice;

import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import roboguice.inject.RoboInjector;

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
        RoboInjector injector = RoboGuice.getInjector(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        name.setText("Hello, " + myName);

        Log.d("TAG", "lol");
    }

    public void lol() {
        if (loc == null)
            loc = (LocationManager) getSystemService(LOCATION_SERVICE);

        for (String provider : loc.getAllProviders()) {
            Log.e("TAG", provider);
        }
        return;
    }
}
