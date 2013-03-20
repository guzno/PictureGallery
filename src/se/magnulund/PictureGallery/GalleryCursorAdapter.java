package se.magnulund.PictureGallery;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

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

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
        // Raw height and width of image
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = widthRatio;
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth);

        Log.e(TAG, "inSampleSize: " + options.inSampleSize);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        Bitmap bitmap;

        bitmap = decodeSampledBitmapFromFile(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)), view.getMeasuredWidth());
        holder.imageView.setImageBitmap(bitmap);

        holder.caption.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
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
