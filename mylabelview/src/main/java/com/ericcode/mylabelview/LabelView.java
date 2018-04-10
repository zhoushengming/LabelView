package com.ericcode.mylabelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaoming on 2018/4/10.
 */

public class LabelView extends View {
    private int labelColor = Color.BLUE;
    private int labelRound = 50;
    private int labelAngle = 40;
    private int labelWidth = 0;
    private int textSize = 0;
    private int textColor = Color.WHITE;
    private int textAngle = 0;
    private String text = "哈哈";
    private Paint textPaint;
    private Paint labelPaint;

    public LabelView(Context context) {
        this(context, null);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        textSize = dip2px(context, 12f);
        labelWidth = dip2px(context, 50f);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
            labelColor = typedArray.getColor(R.styleable.LabelView_labelColor, Color.BLUE);
            labelRound = (int) typedArray.getDimension(R.styleable.LabelView_labelRound, labelRound);
            labelAngle = typedArray.getInt(R.styleable.LabelView_labelAngle, labelAngle);
            labelWidth = (int) typedArray.getDimension(R.styleable.LabelView_labelWidth, labelWidth);
            textSize = (int) typedArray.getDimension(R.styleable.LabelView_textSize, textSize);
            textColor = typedArray.getColor(R.styleable.LabelView_textColor, Color.WHITE);
            textAngle = typedArray.getInt(R.styleable.LabelView_textAngle, 0);
            text = typedArray.getString(R.styleable.LabelView_text);
        }
        textPaint = new Paint();
        labelPaint = new Paint();
        refreshPaints();
    }

    private void refreshPaints() {
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);

        labelPaint.setStyle(Paint.Style.FILL);
        labelPaint.setColor(labelColor);
        labelPaint.setAntiAlias(true);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        Point left_top = new Point(width - labelWidth, 0);
        Point right_top = new Point(width - labelWidth / 4, 0);
        Point bottom = new Point(width, (int) (Math.tan(Math.toRadians(labelAngle)) * labelWidth));
        Point center = new Point(width - labelWidth / 4, (int) (Math.tan(Math.toRadians(labelAngle)) * labelWidth / 4));
        Point bottom_top = new Point(width, bottom.y / 4);
        Point bottom_bottom = new Point(width, bottom.y);

        Path labelPath = new Path();
        labelPath.moveTo(left_top.x, left_top.y);
        labelPath.lineTo(right_top.x, right_top.y);
        labelPath.lineTo(bottom_top.x, bottom_top.y);
        labelPath.lineTo(bottom_bottom.x, bottom_bottom.y);
        labelPath.close();
        canvas.drawPath(labelPath, labelPaint);

        RectF rectF = new RectF(width - labelWidth / 2, 0, width, bottom.y / 2);
        canvas.drawRoundRect(rectF, labelRound, labelRound, labelPaint);
        canvas.save();

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        Point textCenter = new Point(center.x, center.y);
        float textTop = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float textBottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (textCenter.y - textTop / 2 - textBottom / 2);//基线中间点的y轴计算公式
        int textCenterX = (int) (width - ((labelWidth - baseLineY / Math.tan(Math.toRadians(labelAngle))) / 2));

        canvas.rotate(textAngle, textCenterX, textCenter.y);
        if (!TextUtils.isEmpty(text)) {
            canvas.drawText(text, textCenterX, baseLineY, textPaint);
        }

        canvas.restore();
    }

    public int getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
        refreshPaints();
    }

    public int getLabelRound() {
        return labelRound;
    }

    public void setLabelRound(int labelRound) {
        this.labelRound = labelRound;
        refreshPaints();
    }

    public int getLabelAngle() {
        return labelAngle;
    }

    public void setLabelAngle(int labelAngle) {
        this.labelAngle = labelAngle;
        refreshPaints();
    }

    public int getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(int labelWidth) {
        this.labelWidth = labelWidth;
        refreshPaints();
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        refreshPaints();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        refreshPaints();
    }

    public int getTextAngle() {
        return textAngle;
    }

    public void setTextAngle(int textAngle) {
        this.textAngle = textAngle;
        refreshPaints();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        refreshPaints();
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
