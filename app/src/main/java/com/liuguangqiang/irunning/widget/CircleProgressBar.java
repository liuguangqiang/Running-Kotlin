/*
 *
 *  * Copyright 2014-2015 Eric Liu
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.liuguangqiang.irunning.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.liuguangqiang.support.utils.DisplayUtils;

/**
 * Created by Eric on 14/12/27.
 */
public class CircleProgressBar extends View {

  public enum PaintStyle {
    STROKE,
    FILL
  }

  private static final float MAX_SWEEP_ANGLE = 360;

  private static final int DEFAULT_SIZE = 80;
  private static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#089551");
  private static final int DEFAULT_PROGRESS_COLOR = Color.parseColor("#ffffff");
  private int mProgressBackgroundColor = DEFAULT_BACKGROUND_COLOR;
  private int mProgressColor = DEFAULT_PROGRESS_COLOR;
  private int mMax = 100;
  private int mProgress = 50;
  private PaintStyle mPaintStyle = PaintStyle.STROKE;

  private static final int END_ANGLE = 360;

  private Paint mProgressPaint;
  private Paint mProgressBgPaint;

  private RectF mProgressRectF = new RectF(0, 0, 0, 0);
  private RectF mProgressBgRectF = new RectF(0, 0, 0, 0);

  private float mStrokeWith = 10;

  private float unitAngle = 0.0f;

  private float getStrokeOffset() {
    if (mPaintStyle == PaintStyle.FILL) {
      return 0;
    }
    return mStrokeWith / 2;
  }

  public int getMax() {
    return mMax;
  }

  public void setMax(int max) {
    mMax = max;
    setUnitProgress();
    invalidate();
  }

  public int getPrgoress() {
    return mProgress;
  }

  public CircleProgressBar(Context context) {
    this(context, null);
  }

  public CircleProgressBar(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CircleProgressBar(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    mStrokeWith = DisplayUtils.dip2px(getContext(), 2);
    initPaint();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    setUnitProgress();
  }

  private int measure(int measureSpec) {
    int result;
    int mode = MeasureSpec.getMode(measureSpec);
    int size = MeasureSpec.getSize(measureSpec);
    if (mode == MeasureSpec.EXACTLY) {
      result = size;
    } else {
      result = DisplayUtils.dip2px(getContext(), DEFAULT_SIZE);
    }
    return result;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    drawProgressBg(canvas);
    drawProgress(canvas);
  }

  private void initPaint() {
    //Progress
    mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mProgressPaint.setColor(mProgressColor);

    //Progress Background
    mProgressBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mProgressBgPaint.setColor(mProgressBackgroundColor);

    if (mPaintStyle == PaintStyle.STROKE) {
      DashPathEffect dashPath = new DashPathEffect(new float[]{mStrokeWith, mStrokeWith * 3.5f},
          0.0f);

      mProgressPaint.setStyle(Paint.Style.STROKE);
      mProgressPaint.setStrokeWidth(mStrokeWith);
      mProgressPaint.setPathEffect(dashPath);

      dashPath = new DashPathEffect(new float[]{mStrokeWith, mStrokeWith * 3.5f}, 0.0f);
      mProgressBgPaint.setStyle(Paint.Style.STROKE);
      mProgressBgPaint.setStrokeWidth(mStrokeWith);
      mProgressBgPaint.setPathEffect(dashPath);
    }
  }

  private void drawProgress(Canvas canvas) {
    mProgressRectF.left = getPaddingLeft() + getStrokeOffset();
    mProgressRectF.top = getPaddingTop() + getStrokeOffset();
    mProgressRectF.right = getWidth() - getPaddingLeft() - getPaddingRight() - getStrokeOffset();
    mProgressRectF.bottom = getHeight() - getStrokeOffset();
    canvas
        .drawArc(mProgressRectF, 0, getSweepAngel(), getUseCenter(), mProgressPaint);
  }

  private void drawProgressBg(Canvas canvas) {
    mProgressBgRectF.left = getPaddingLeft() + getStrokeOffset();
    mProgressBgRectF.top = getPaddingTop() + getStrokeOffset();
    mProgressBgRectF.right = getWidth() - getPaddingLeft() - getPaddingRight() - getStrokeOffset();
    mProgressBgRectF.bottom = getHeight() - getStrokeOffset();
    canvas.drawArc(mProgressBgRectF, 0, END_ANGLE, getUseCenter(), mProgressBgPaint);
  }

  private boolean getUseCenter() {
    return mPaintStyle == PaintStyle.FILL;
  }

  private void setUnitProgress() {
    unitAngle = MAX_SWEEP_ANGLE / mMax;
  }

  public void setProgress(int progress) {
    mProgress = progress;
    invalidate();
  }

  public void setProgressWithAnim(int progress, long duration) {
    setProgressWithAnim(0, progress, duration);
  }

  public void setProgressWithAnim(int fromProgress, int toProgress, long duration) {
    ValueAnimator animator = ValueAnimator.ofInt(fromProgress, toProgress);
    animator.setDuration(duration);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        setProgress((int) animation.getAnimatedValue());
      }
    });
    animator.setInterpolator(new LinearInterpolator());
    animator.start();
  }

  private int getSweepAngel() {
    return (int) (unitAngle * mProgress);
  }

}
