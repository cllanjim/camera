package cc.fotoplace.camera.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class OverlayRenderer implements RenderOverlay.Renderer {

    private static final String TAG = "CAM OverlayRenderer";
    protected RenderOverlay mOverlay;

    protected int mLeft, mTop, mRight, mBottom;

    protected boolean mVisible;

    public void setVisible(boolean vis) {
        mVisible = vis;
        update();
    }

    public boolean isVisible() {
        return mVisible;
    }

    // default does not handle touch
    @Override
    public boolean handlesTouch() {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        return false;
    }

    public abstract void onDraw(Canvas canvas);

    @SuppressLint("WrongCall")
	public void draw(Canvas canvas) {
        if (mVisible) {
            onDraw(canvas);
        }
    }

    @Override
    public void setOverlay(RenderOverlay overlay) {
        mOverlay = overlay;
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        mLeft = left;
        mRight = right;
        mTop = top;
        mBottom = bottom;
    }

    protected Context getContext() {
        if (mOverlay != null) {
            return mOverlay.getContext();
        } else {
            return null;
        }
    }

    public int getWidth() {
        return mRight - mLeft;
    }

    public int getHeight() {
        return mBottom - mTop;
    }

    protected void update() {
        if (mOverlay != null) {
            mOverlay.update();
        }
    }

}
