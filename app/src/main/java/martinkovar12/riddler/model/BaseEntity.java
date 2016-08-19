package martinkovar12.riddler.model;

import android.provider.BaseColumns;

import java.util.Date;

public abstract class BaseEntity
{
	//region Fields
	@Column(name = BaseColumns._ID)
	private int m_id;
	@Column(name = "name")
	private String m_name;
	@Column(name = "insertedOn")
	private Date m_insertedOn;
	@Column(name = "modifiedOn")
	private Date m_modifiedOn;
	@Column(name = "valid")
	private int m_valid;
	//endregion

	//region Constructors
	public BaseEntity()
	{
		m_valid = 1;
	}
	//endregion

	//region Methods
	public int getId()
	{
		return m_id;
	}

	public void setId(int id)
	{
		m_id = id;
	}

	public String getName()
	{
		return m_name;
	}

	public void setName(String name)
	{
		m_name = name;
	}

	public Date getInsertedOn()
	{
		return m_insertedOn;
	}

	public void setInsertedOn(Date insertedOn)
	{
		m_insertedOn = insertedOn;
	}

	public Date getModifiedOn()
	{
		return m_modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn)
	{
		m_modifiedOn = modifiedOn;
	}

	public int getValid()
	{
		return m_valid;
	}

	public void setValid(int valid)
	{
		m_valid = valid;
	}
	//endregion
}