package martinkovar12.riddler.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ProgressView extends View
{
	private float m_percent = 30;
	private float m_cornerRadius;
	private RectF m_rect = new RectF();
	private Paint m_paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public ProgressView(Context context)
	{
		super(context);
		initialize();
	}

	public ProgressView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initialize();
	}

	public ProgressView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		initialize();
	}

	private void initialize()
	{
		m_paint.setColor(Color.argb(100, 200, 20, 20));

		Resources r = getResources();
		m_cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, r.getDisplayMetrics());
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		float rectWidth = getWidth() * m_percent / 100;
		m_rect.set(0, 0, rectWidth, getHeight());
		canvas.drawRoundRect(m_rect, m_cornerRadius, m_cornerRadius, m_paint);
	}
}