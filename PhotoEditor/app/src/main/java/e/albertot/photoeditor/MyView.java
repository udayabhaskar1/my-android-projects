package e.albertot.photoeditor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Alberto T on 1/4/2018.
 */

public class MyView extends View {
    private Paint paint;
    private ArrayList<PointF> points;
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    public void setPoints(ArrayList<PointF> point) {
        points = point;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (points == null) return;
        for (PointF point : points) {
            RectF rect = new RectF(point.x, point.y, point.x + 20, point.y + 20);
            canvas.drawRect(rect, paint);
        }
    }
}
