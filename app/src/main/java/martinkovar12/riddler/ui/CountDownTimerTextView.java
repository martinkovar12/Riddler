package martinkovar12.riddler.ui;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CountDownTimerTextView extends TextView {

    private static final SimpleDateFormat m_format = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    //region Fields
    private CountDownTimer m_timer;
    private OnCountdownFinishedListener m_onCountDownFinishedListener;
    //endregion

    //region Constructors
    public CountDownTimerTextView(Context context) {
        super(context);
    }

    public CountDownTimerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTimerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //endregion

    //region Properties
    public OnCountdownFinishedListener getOnCountdownFinishedListener() {
        return m_onCountDownFinishedListener;
    }

    public void setOnCountdownFinishedListener(OnCountdownFinishedListener onCountdownFinishedListener) {
        m_onCountDownFinishedListener = onCountdownFinishedListener;
    }
    //endregion

    //region Overrides
    public void start(long millisInFuture, long countDownInterval) {
        if (m_timer != null) {
            m_timer.cancel();
            m_timer = null;
        }

        m_timer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateText(toIntExact(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                if (m_onCountDownFinishedListener != null) {
                    m_onCountDownFinishedListener.onCountdownFinished(CountDownTimerTextView.this);
                }
            }
        };

        m_timer.start();
    }
    //endregion

    //region Methods
    protected void updateText(int ms) {
        Calendar c = getCalendar(ms);
        setText(m_format.format(c.getTime()));
    }

    @NonNull
    private Calendar getCalendar(int ms) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, ms);
        return c;
    }

    protected int toIntExact(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Unable to convert " + l + " to int.");
        }
        return (int) l;
    }
    //endregion

    //region Nested Classes
    public interface OnCountdownFinishedListener {
        void onCountdownFinished(CountDownTimerTextView countDownTimerTextView);
    }
    //endregion
}