package se.magnulund.PictureGallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import se.magnulund.PictureGallery.views.GalleryImageView;

import java.lang.ref.WeakReference;

public class BitmapLoaderTask extends AsyncTask<BitmapLoaderTask.BitmapLoaderParams, Void, Bitmap> {

    private static final String TAG = "BitmapLoaderTask";

    private final WeakReference<GalleryImageView> imageViewReference;
    BitmapLoaderParams bitmapLoaderParams;
    int imageId;

    public BitmapLoaderTask(GalleryImageView imageView, int imageId) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<GalleryImageView>(imageView);
        this.imageId = imageId;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();    //To change body of overridden methods use File | Settings | File Templates.
        if (imageViewReference == null || imageId != imageViewReference.get().getImageID()) {
            Log.e(TAG, "Load of image: "+imageId+"cancelled");
            cancel(false);
        }
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
            final GalleryImageView imageView = imageViewReference.get();
            if (imageView != null) {
                Log.e(TAG, "Applying bitmap to image: "+imageId);
                View parent = (View) imageView.getParent();
                parent.setMinimumHeight(0);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public BitmapLoaderParams getBitmapLoaderParams(String path, int imageWidth, int reqWidth) {
        return new BitmapLoaderParams(path, imageWidth, reqWidth);
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
        if (imageWidth == 0) {

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
}

