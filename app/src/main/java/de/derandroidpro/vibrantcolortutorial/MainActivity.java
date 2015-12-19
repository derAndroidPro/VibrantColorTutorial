package de.derandroidpro.vibrantcolortutorial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    int vibrantcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(loadBitmapAndGetVibrant(R.drawable.forest));
    }

    public Bitmap loadBitmapAndGetVibrant(int imageId){
        Bitmap bm = BitmapFactory.decodeResource(getResources(), imageId);
        Palette palette = Palette.from(bm).generate();
        vibrantcolor = palette.getVibrantColor(Color.GRAY);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(vibrantcolor));
        if(Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            window.setStatusBarColor(darkenColor(vibrantcolor));
        }

        return bm;
    }

    public int darkenColor(int color){

        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }
}
