package jzlview.jzl.com.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2016/5/7 0007.
 */
public class LogicView extends View{

    private Paint paint = new Paint();
    private float rx = 0;
    private MyThread Thread;
    private RectF rectF  = new RectF(0, 60, 100, 160);
    private float sweepAngle = 0;

    public LogicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogicView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(30);
        canvas.drawText("LogicView", rx, 30, paint);

        canvas.drawArc(rectF, 0, sweepAngle, true, paint);     // 绘制圆

        if(Thread == null) {
            Thread = new MyThread();
            Thread.start();          // 调用start方法会自动调用run方法
        }

    }

    class MyThread extends Thread{     // 创建线程
        Random random = new Random();
        @Override
        public void run() {

            while (true) {
                rx += 3;             // 在线程中改变坐标以防止其同时在layout中出现
                postInvalidate();          // 进行重新绘制（实际上重新调用了onDraw方法）

                if(rx > getWidth()) {  // 如果超出屏幕宽度
                    rx = 0 - paint.measureText("LogicView");  // 让所有字体从屏幕左侧滚出
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

                postInvalidate();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }
    }

}
























