package com.vaibhav.inshorts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SlidePageAdapter extends PagerAdapter {

    private Context context;
    private int[] imageImageView = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3
    };

    private String[] subHeadingTextView;
    private String[] contentTextView;
    private String[] readMoreTextView;

    SlidePageAdapter(Context context) {
        this.context = context;

        subHeadingTextView = context.getResources().getStringArray(R.array.subHeading);
        contentTextView = context.getResources().getStringArray(R.array.content);
        readMoreTextView = context.getResources().getStringArray(R.array.readMore);
    }

    @Override
    public int getCount() {
        return imageImageView.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = Objects.requireNonNull(layoutInflater).inflate(R.layout.card_layout, container, false);

        final ImageView image = view.findViewById(R.id.image);
        final ImageView readMoreImage = view.findViewById(R.id.read_more_image);
        final TextView heading = view.findViewById(R.id.subheading);
        final TextView content = view.findViewById(R.id.content);
        final TextView readMore = view.findViewById(R.id.read_more);


        image.setImageResource(imageImageView[position]);

        Drawable drawable = view.getResources().getDrawable(imageImageView[position]);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap blurredBitmap = BlurBuilder.blur(context, bitmap);
        readMoreImage.setImageBitmap(blurredBitmap);
        readMoreImage.setColorFilter(0x76AAAAAA, PorterDuff.Mode.MULTIPLY);

        heading.setText(subHeadingTextView[position]);
        content.setText(contentTextView[position]);
        readMore.setText(readMoreTextView[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((FrameLayout) object);
    }
}
