package se.magnulund.PictureGallery.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created with IntelliJ IDEA.
 * User: Gustav
 * Date: 21/03/2013
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class GalleryImageView extends ImageView {
    private static final String TAG = "GalleryImageView";

    private int imageID = 0;
    private String imagePath = "";


    private int imageWidth = 0;

    public GalleryImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
