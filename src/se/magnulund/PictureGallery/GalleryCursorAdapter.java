package se.magnulund.PictureGallery;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import se.magnulund.PictureGallery.views.GalleryImageView;

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
        GalleryImageView imageView;
    }




    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        holder.imageView.setImageBitmap(null);

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        int imageWidth = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.WIDTH));
        int imageHeight= cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.HEIGHT));
        int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
        int reqWidth = view.getMeasuredWidth();

        Log.e(TAG, "width: "+reqWidth);

        holder.imageView.setImageID(id);
        holder.imageView.setImagePath(path);
        holder.imageView.setImageFullWidth(imageWidth);

        view.setMinimumHeight(reqWidth);

        BitmapLoaderTask.BitmapLoaderParams bitmapLoaderParams = new BitmapLoaderTask.BitmapLoaderParams(path, imageWidth, reqWidth);
        BitmapLoaderTask bitmapLoaderTask = new BitmapLoaderTask(holder.imageView, id);
        bitmapLoaderTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, bitmapLoaderParams);

        holder.caption.setText(imageWidth+"X"+imageHeight);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gallery_list_item, parent, false);

        ViewHolder holder = new ViewHolder();

        holder.caption = (TextView) view.findViewById(R.id.caption);
        holder.imageView = (GalleryImageView) view.findViewById(R.id.image_view);

        view.setTag(holder);

        return view;
    }
}

