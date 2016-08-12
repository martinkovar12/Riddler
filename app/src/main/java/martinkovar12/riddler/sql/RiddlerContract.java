package martinkovar12.riddler.sql;

import android.content.ContentValues;
import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RiddlerContract
{
	//region Constants
	private static final SimpleDateFormat s_simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
	private static final String INT = " INTEGER";
	private static final String REA = " REAL";
	private static final String TXT = " TEXT";
	private static final String SQL_CRE_TAB = "CREATE TABLE ";
	private static final String SQL_DRO_TAB = "DROP TABLE IF EXISTS ";
	private static final String SQL_SYS_COLS = SystemColumns._ID + TXT + " PRIMARY KEY," +
			SystemColumns._NAME + TXT + ',' +
			SystemColumns._VALID + INT + ',' +
			SystemColumns._INSERTED_ON + TXT + ',' +
			SystemColumns._MODIFIED_ON + TXT;
	//endregion

	//region Methods
	public static ContentValues createInsertSystemContentValues(String name)
	{
		Date now = new Date();
		String nowFormatted = s_simpleDateFormat.format(now);

		ContentValues values = new ContentValues();
		values.put(SystemColumns._NAME, name);
		values.put(SystemColumns._INSERTED_ON, nowFormatted);
		values.put(SystemColumns._MODIFIED_ON, nowFormatted);
		values.put(SystemColumns._VALID, 1);
		return values;
	}
	//endregion

	public static ContentValues createUpdateSystemContentValues()
	{
		Date now = new Date();
		String nowFormatted = s_simpleDateFormat.format(now);

		ContentValues values = new ContentValues();
		values.put(SystemColumns._MODIFIED_ON, nowFormatted);
		return values;
	}

	//region Interfaces
	private interface SystemColumns extends BaseColumns
	{
		String _NAME = "name";
		String _INSERTED_ON = "inserted_on";
		String _MODIFIED_ON = "modified_on";
		String _VALID = "valid";
	}
	//endregion

	//region Entries
	public static class Game implements SystemColumns
	{
		public static final String TAB_NAME = "Game";
		static final String SQL_CRE = SQL_CRE_TAB + TAB_NAME + " (" + SQL_SYS_COLS + ')';
		static final String SQL_DRO = SQL_DRO_TAB + TAB_NAME;
	}

	public static class Team implements SystemColumns
	{
		public static final String TAB_NAME = "Team";
		public static final String COL_GAME_ID = "game_id";
		public static final String COL_SCORE = "score";
		public static final String COL_IS_ON_TURN = "is_on_turn";
		static final String SQL_CRE = SQL_CRE_TAB + TAB_NAME + " (" + SQL_SYS_COLS + ',' +
				COL_GAME_ID + INT + ',' +
				COL_SCORE + INT + ',' +
				COL_IS_ON_TURN + INT + ')';
		static final String SQL_DRO = SQL_DRO_TAB + TAB_NAME;
	}
	//endregion
}