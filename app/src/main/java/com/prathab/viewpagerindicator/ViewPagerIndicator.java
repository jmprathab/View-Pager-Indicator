package com.prathab.viewpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ViewPagerIndicator extends View {
  private static final int PAGE_COUNT_DEFAULT_VALUE = 3;
  private static final int CURRENT_PAGE_DEFAULT_VALUE = 1;
  private static final int RADIUS_DEFAULT_VALUE = 10;
  private static final int COLOR_DEFAULT_VALUE = Color.YELLOW;
  private static final int INTERVAL_DEFAULT_VALUE = 50;

  private float x, y;
  private int userInputPageCount;
  private int userInputCurrentPage;
  private float userInputRadius;
  private float userInputInterval;
  private int userInputColor;

  private int i; //Variable used for looping in onDraw

  private Paint painterForNormalIndicators, painterForFilledIndicators;

  public ViewPagerIndicator(Context context) {
    super(context);
  }

  public ViewPagerIndicator(Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray typedArray =
        context.getTheme().obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, 0, 0);

    try {
      userInputPageCount =
          typedArray.getInt(R.styleable.ViewPagerIndicator_pageCount, PAGE_COUNT_DEFAULT_VALUE);
      userInputCurrentPage =
          typedArray.getInt(R.styleable.ViewPagerIndicator_currentPage, CURRENT_PAGE_DEFAULT_VALUE);
      userInputRadius =
          typedArray.getDimension(R.styleable.ViewPagerIndicator_radius, RADIUS_DEFAULT_VALUE);
      userInputColor =
          typedArray.getColor(R.styleable.ViewPagerIndicator_color, COLOR_DEFAULT_VALUE);
      userInputInterval =
          typedArray.getDimension(R.styleable.ViewPagerIndicator_interval, INTERVAL_DEFAULT_VALUE);

      painterForNormalIndicators = new Paint(Paint.ANTI_ALIAS_FLAG);
      painterForNormalIndicators.setStyle(Paint.Style.STROKE);
      painterForNormalIndicators.setColor(userInputColor);

      painterForFilledIndicators = new Paint(Paint.ANTI_ALIAS_FLAG);
      painterForFilledIndicators.setColor(userInputColor);
      painterForFilledIndicators.setStyle(Paint.Style.FILL);
    } finally {
      typedArray.recycle();
    }
  }

  @Override protected void onDraw(final Canvas canvas) {
    super.onDraw(canvas);

    x = userInputRadius + getPaddingLeft();
    y = userInputRadius + getPaddingTop();

    for (i = 1; i <= userInputPageCount; i++) {
      if (i == userInputCurrentPage) {
        canvas.drawCircle(x, y, userInputRadius, painterForFilledIndicators);
      } else {
        canvas.drawCircle(x, y, userInputRadius, painterForNormalIndicators);
      }
      x = x + userInputInterval;
    }
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int totalXSize = 0;
    int totalYSize = 0;

    totalXSize += getPaddingLeft() + getPaddingRight();
    totalYSize += getPaddingBottom() + getPaddingTop();

    totalYSize += userInputRadius + userInputRadius;
    totalXSize += (userInputPageCount - 1) * userInputInterval + 2 * userInputRadius;

    setMeasuredDimension(totalXSize, totalYSize);
  }

  public int getCurrentPage() {
    return userInputCurrentPage;
  }

  public void setCurrentPage(int userInputCurrentPage) {
    if ((userInputCurrentPage) > 0 && (userInputCurrentPage <= userInputPageCount)) {
      this.userInputCurrentPage = userInputCurrentPage;
    }
    invalidate();
  }

  public boolean isLastPage() {
    return userInputCurrentPage == userInputPageCount;
  }

  public boolean isFirstPage() {
    return userInputCurrentPage == 1;
  }

  public void goToNextPage() {
    setCurrentPage(getCurrentPage() + 1);
  }

  public void goToPreviousPage() {
    setCurrentPage(getCurrentPage() - 1);
  }
}
