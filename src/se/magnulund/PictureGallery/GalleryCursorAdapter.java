package se.magnulund.PictureGallery;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: erikeelde
 * Date: 20/3/2013
 * Time: 01:16
 * To change this template use File | Settings | File Templates.
 */
public class GalleryCursorAdapter extends CursorAdapter {
    private static final String TAG = "GalleryCursorAdapter";

    public GalleryCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    private class ViewHolder {
        TextView caption;
        ImageView imageView;
    }




    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        Bitmap bitmap;

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        int imageWidth = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.WIDTH));
        int imageHeight= cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.HEIGHT));
        int reqWidth = view.getMeasuredWidth();

        BitmapLoaderTask.BitmapLoaderParams bitmapLoaderParams = new BitmapLoaderTask.BitmapLoaderParams(path, imageWidth, reqWidth);
        BitmapLoaderTask bitmapLoaderTask = new BitmapLoaderTask(holder.imageView);
        bitmapLoaderTask.execute(bitmapLoaderParams);

        holder.caption.setText(imageWidth+"X"+imageHeight);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gallery_list_item, parent, false);

        ViewHolder holder = new ViewHolder();

        holder.caption = (TextView) view.findViewById(R.id.caption);
        holder.imageView = (ImageView) view.findViewById(R.id.imageView);

        view.setTag(holder);

        return view;
    }
}

