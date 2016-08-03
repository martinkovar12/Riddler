package martinkovar12.riddler.ui;

import android.content.Context;
import android.util.AttributeSet;

import java.util.Calendar;

public class Countdown extends Watch {

    private int m_countdownMs;
    private OnCountdownFinishedListener m_onCountdownFinishedListener;

    public Countdown(Context context) {
        super(context);
    }

    public Countdown(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Countdown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCountdownMs(int countdownMs) {
        m_countdownMs = countdownMs;
    }

    public void setOnCountdownFinishedListener(OnCountdownFinishedListener onCountdownFinishedListener) {
        m_onCountdownFinishedListener = onCountdownFinishedListener;
    }

    @Override
    protected void updateText() {
        int remainingMs = m_countdownMs - toIntExact(m_elapsedMs);
        Calendar c = getCalendar(remainingMs);
        setText(m_format.format(c.getTime()));
    }

    public interface OnCountdownFinishedListener {
        void onCountdownFinished(Countdown countdown);
    }
}