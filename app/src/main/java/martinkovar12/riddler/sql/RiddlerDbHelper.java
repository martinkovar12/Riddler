package martinkovar12.riddler.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RiddlerDbHelper extends SQLiteOpenHelper
{
	//region Constants
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "Riddler.db";
	//endregion

	//region Constructors
	public RiddlerDbHelper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}
	//endregion

	//region Implementations
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(RiddlerContract.Game.SQL_CRE);
		db.execSQL(RiddlerContract.Team.SQL_CRE);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL(RiddlerContract.Game.SQL_DRO);
		db.execSQL(RiddlerContract.Team.SQL_DRO);
		onCreate(db);
	}
	//endregion
}
