package martinkovar12.riddler.ui;

import android.content.Context;
import android.util.AttributeSet;

import java.util.Calendar;

public class Stopwatch extends Watch {

    public Stopwatch(Context context) {
        super(context);
    }

    public Stopwatch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Stopwatch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void updateText() {
        Calendar c = getCalendar(toIntExact(m_elapsedMs));
        setText(m_format.format(c.getTime()));
    }

    public void reset() {
        super.reset();
        Calendar c = getCalendar(0);
        setText(m_format.format(c.getTime()));
    }
}