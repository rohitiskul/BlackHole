package com.github.rkcorp.blackhole;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * To show something cool
 * Created by Rohit Kulkarni on 30/11/15.
 */
public final class BlackHole extends View {

    private Paint eraser;
    private Canvas canvasBitmap;
    private Bitmap bmp;
    private int mainColor;
    private View focusView, parentView;

    public BlackHole(Context context) {
        super(context);
        init(context, null);
    }

    public BlackHole(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BlackHole(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        if (isInEditMode())
            return;
        eraser = new Paint();
        eraser.setColor(0xFFFFFFFF);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mainColor = Color.parseColor("#AB000000");
        if (attrs != null) {
            final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BlackHole);
            mainColor = typedArray.getColor(R.styleable.BlackHole_black_hole_color, mainColor);
            typedArray.recycle();
        }

    }

    public void setFocusView(final View view) {
        this.focusView = view;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bmp == null) {
            bmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            canvasBitmap = new Canvas(bmp);
            parentView = (View) getParent();
        }
        bmp.eraseColor(mainColor);
        int pL = parentView.getPaddingLeft();
        int pT = parentView.getPaddingTop();
        int wd = focusView.getWidth();
        int ht = focusView.getHeight();

        float left = focusView.getLeft() - pL;
        float top = focusView.getTop() - pT;
        float right = left + wd;
        float bottom = top + ht;

        canvasBitmap.drawRect(left, top, right, bottom, eraser);
        canvas.drawBitmap(bmp, 0, 0, null);
    }
}
