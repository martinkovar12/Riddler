package martinkovar12.riddler;

import android.provider.BaseColumns;

public class Contract
{
	//region Constants
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
		public static final String SQL_CRE = SQL_CRE_TAB + TAB_NAME + " (" + SQL_SYS_COLS + ')';
		public static final String SQL_DRO = SQL_DRO_TAB + TAB_NAME;
	}

	public static class Team implements SystemColumns
	{
		public static final String TAB_NAME = "Team";
		public static final String COL_GAME_ID = "game_id";
		public static final String SQL_CRE = SQL_CRE_TAB + TAB_NAME + " (" + SQL_SYS_COLS + ',' + COL_GAME_ID + INT + ')';
		public static final String SQL_DRO = SQL_DRO_TAB + TAB_NAME;
	}
	//endregion
}