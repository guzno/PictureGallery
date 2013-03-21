package se.magnulund.PictureGallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class BitmapLoaderTask extends AsyncTask<BitmapLoaderTask.BitmapLoaderParams, Void, Bitmap> {

    private static final String TAG = "BitmapLoaderTask";

    private final WeakReference<ImageView> imageViewReference;
    BitmapLoaderParams bitmapLoaderParams;

    public BitmapLoaderTask(ImageView imageView) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    public static int calculateInSampleSize(int imageWidth, int reqWidth) {
        // Raw height and width of image
        int inSampleSize = 1;

        if (imageWidth > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int widthRatio = Math.round((float) imageWidth / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = widthRatio;
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int imageWidth, int reqWidth) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        if ( imageWidth == 0 ) {

            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            imageWidth = options.outWidth;
        }

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(imageWidth, reqWidth);

        Log.e(TAG, "inSampleSize: " + options.inSampleSize);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(BitmapLoaderParams... params) {
        bitmapLoaderParams = params[0];
        return decodeSampledBitmapFromFile(bitmapLoaderParams.path, bitmapLoaderParams.imageWidth, bitmapLoaderParams.reqWidth);
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public static class BitmapLoaderParams {
        String path;
        int imageWidth;
        int reqWidth;

        BitmapLoaderParams(String path, int imageWidth, int reqWidth) {
            this.path = path;
            this.imageWidth = imageWidth;
            this.reqWidth = reqWidth;
        }
    }
}

