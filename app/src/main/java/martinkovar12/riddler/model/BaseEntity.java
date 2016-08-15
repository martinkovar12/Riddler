package martinkovar12.riddler.model;

import android.provider.BaseColumns;

public class BaseEntity implements BaseColumns
{
	private String m_name;
	private String m_inserted_on;
	private String m_modified_on;
	private int m_valid;
}