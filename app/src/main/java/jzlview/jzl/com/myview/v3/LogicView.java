package jzlview.jzl.com.myview.v3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import java.util.Random;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public class LogicView extends BaseView{

    private Paint paint = new Paint();
    private float rx = 0;
    //private MyThread Thread;
    private RectF rectF  = new RectF(0, 60, 100, 160);
    private float sweepAngle = 0;
    Random random = new Random();

    public LogicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawSub(Canvas canvas) {
        paint.setTextSize(30);
        canvas.drawText("罗健顺是沙雕", rx, 30, paint);

        canvas.drawArc(rectF, 0, sweepAngle, true, paint);     // 绘制圆
    }

    @Override
    protected void logic() {
        rx += 3;             // 在线程中改变坐标以防止其同时在layout中出现
        postInvalidate();          // 进行重新绘制（实际上重新调用了onDraw方法）

        if(rx > getWidth()) {  // 如果超出屏幕宽度
            rx = 0 - paint.measureText("罗健顺是沙雕");  // 让所有字体从屏幕左侧滚出
        }

        float tangle;
        tangle = sweepAngle;

        if(sweepAngle < 360) {
            tangle ++;

        } else {
            tangle = -sweepAngle;
        }
        sweepAngle = tangle;

        int r = random.nextInt(256);     // 自定义一个随机值
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        paint.setARGB(255, r, g, b);     // 随机生成一个颜色
    }
}
