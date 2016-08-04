package martinkovar12.riddler.ui;

import android.content.Context;
import android.util.AttributeSet;

public class Countdown extends Watch {

    //region Fields
    private int m_countdownMs;
    private OnCountdownFinishedListener m_onCountdownFinishedListener;
    //endregion

    //region Constructors
    public Countdown(Context context) {
        super(context);
    }

    public Countdown(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Countdown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //endregion

    //region Properties
    public void setCountdownMs(int countdownMs) {
        m_countdownMs = countdownMs;
    }

    public OnCountdownFinishedListener getOnCountdownFinishedListener() {
        return m_onCountdownFinishedListener;
    }

    public void setOnCountdownFinishedListener(OnCountdownFinishedListener onCountdownFinishedListener) {
        m_onCountdownFinishedListener = onCountdownFinishedListener;
    }
    //endregion

    //region Overrides
    @Override
    protected void afterDelay() {
        int remainingMs = m_countdownMs - toIntExact(getElapsedMs());
        if (remainingMs > 0) {
            updateText(remainingMs);
        } else {
            updateText(0);
            stop();
        }
    }
    //endregion

    //region Nested Classes
    public interface OnCountdownFinishedListener {
        void onCountdownFinished(Countdown countdown);
    }
    //endregion
}