package martinkovar12.riddler.ui;

import android.content.Context;
import android.util.AttributeSet;

public class Stopwatch extends Watch {

    //region Constructors
    public Stopwatch(Context context) {
        super(context);
    }

    public Stopwatch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Stopwatch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //endregion

    //region Overrides
    @Override
    protected void afterDelay() {
        updateText(toIntExact(getElapsedMs()));
    }

    public void reset() {
        super.reset();
        updateText(0);
    }
    //endregion
}