package com.lnjm.percentview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class PresentView extends View {

    private Paint paint;
    Paint paintRight;
    Paint paintCircle;
    Paint paintStroke;//中间圆描边

    private int height;
    private int width;

    float radius = 60f;//圆角，圆的半径

    float rate = 0.5f;
    int leftColor = Color.RED,//左边颜色
            rightColor = Color.BLUE,//右边颜色
            textColor = Color.RED;//文字颜色

    String text="PK";
    Paint paintText;

    public PresentView(Context context) {
        super(context);
        init();
    }

    public PresentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PresentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        paint = new Paint();
        paint.setColor(leftColor);
        paint.setAntiAlias(true);

        paintRight = new Paint();
        paintRight.setColor(rightColor);
        paintRight.setAntiAlias(true);

        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.FILL_AND_STROKE);
        paintCircle.setColor(Color.WHITE);

        paintStroke = new Paint();
        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setColor(Color.parseColor("#cccccc"));
        paintStroke.setStrokeWidth(2);
        paintStroke.setAntiAlias(true);

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setColor(textColor);
        paintText.setTextSize(radius / 3 * 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(0, 0, width * rate + radius / 2, height, radius, radius
                , paint);

        canvas.drawRoundRect(width * rate - radius / 2, 0, width, height, radius, radius
                , paintRight);

        canvas.drawCircle(width * rate, height / 2.0f, height / 2, paintCircle);

        canvas.drawCircle(width * rate, height / 2.0f, height / 2, paintStroke);

        Paint.FontMetrics fontMetrics = paintText.getFontMetrics();
        float y = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        float textWidth = paintText.measureText(text);
        canvas.drawText(text, width * rate - textWidth / 2.0f, y, paintText);
    }

    /***
     * 设置比例
     * @param rate 比例值
     */
    public void setRate(float rate) {
        this.rate = rate;
        invalidate();
    }

    /***
     *
     * @param leftColor
     * @param right
     * @param textColor
     */
   public void setColor(int leftColor,int right ,int textColor,String text){
        this.leftColor=leftColor;
        this.rightColor=right;
        this.textColor=textColor;
        this.text=text;

        init();
    }
}
