package ua.edu.tntu.news;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.DiscCacheUtil;
import com.nostra13.universalimageloader.core.assist.MemoryCacheUtil;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

    static final List<String> displayedImages =
            Collections.synchronizedList(new LinkedList<String>());

    boolean cacheFound;

    @Override
    public void onLoadingStarted(String url, View view) {
        List<String> memCache = MemoryCacheUtil.
                findCacheKeysForImageUri(url, ImageLoader.getInstance().getMemoryCache());
        cacheFound = !memCache.isEmpty();
        if (!cacheFound) {
            File discCache = DiscCacheUtil.
                    findInCache(url, ImageLoader.getInstance().getDiscCache());
            if (discCache != null) {
                cacheFound = discCache.exists();
            }
        }
    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        if (loadedImage != null) {
            ImageView imageView = (ImageView) view;
            boolean firstDisplay = !displayedImages.contains(imageUri);
            if (firstDisplay) {
                FadeInBitmapDisplayer.animate(imageView, 500);
                displayedImages.add(imageUri);
            }
        }
    }
}
