package martinkovar12.riddler.ui;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public abstract class Watch extends TextView {

    //region Fields
    private long m_startMs;
    private long m_elapsedMs;
    private boolean m_stopped = false;
    private SimpleDateFormat m_format = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    private Handler m_handler = new Handler();
    private Runnable m_runnable = new Runnable() {
        public void run() {
            m_elapsedMs = System.currentTimeMillis() - m_startMs;
            afterDelay();
            m_handler.postDelayed(this, 100);
        }
    };
    //endregion

    //region Constructors
    public Watch(Context context) {
        super(context);
    }

    public Watch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Watch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //endregion

    //region Properties
    public long getElapsedMs() {
        return m_elapsedMs;
    }
    //endregion

    //region Public Methods
    public void start() {
        if (m_stopped) {
            m_startMs = System.currentTimeMillis() - m_elapsedMs;
        } else {
            m_startMs = System.currentTimeMillis();
        }
        m_handler.removeCallbacks(m_runnable);
        m_handler.postDelayed(m_runnable, 0);
    }

    public void stop() {
        m_handler.removeCallbacks(m_runnable);
        m_stopped = true;
    }

    public void reset() {
        m_stopped = false;
    }
    //endregion

    //region Methods
    protected abstract void afterDelay();

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
}