package se.magnulund.PictureGallery.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GalleryImageView extends ImageView {
    private static final String TAG = "GalleryImageView";

    private int imageID = 0;
    private String imagePath = "";
    private int imageWidth = 0;

    public GalleryImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageID(int imageID){
        this.imageID = imageID;
    }

    public int getImageID(){
        return imageID;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public int getImageFullWidth() {
        return imageWidth;
    }

    public void setImageFullWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }
}
