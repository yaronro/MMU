package com.hit.driver;



import java.io.Serializable;


@SuppressWarnings("serial")
public class DataModel<T> extends Object implements Serializable {

	private Long dataModelId;
	private T content;

	public DataModel (Long dataModelId, T content ) {
		this.dataModelId = dataModelId;
		this.content = content;
	}

	public void setDataModelId(Long id)
	{
		this.dataModelId = id;
	}

	public void setDataModelContent(T content)
	{
		this.content = content;
	}


	public long getDataModelId() {
		return dataModelId;
	}

	public T getDataModelContent()
	{
		return content;
	}

	@Override
	public String toString() {
		return "DataModel [dataModelId=" + dataModelId + ", content=" + content + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dataModelId == null) ? 0 : dataModelId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		DataModel other = (DataModel) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (dataModelId == null) {
			if (other.dataModelId != null)
				return false;
		} else if (!dataModelId.equals(other.dataModelId))
			return false;
		return true;
	}

}