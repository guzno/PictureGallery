package se.magnulund.PictureGallery;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        TextView text1;
        TextView text2;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.text1.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
        holder.text2.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gallery_list_item, parent, false);

        ViewHolder holder = new ViewHolder();

        holder.text1 = (TextView)view.findViewById(android.R.id.text1);
        holder.text2 = (TextView)view.findViewById(android.R.id.text2);

        view.setTag(holder);

        return view;
    }
}
