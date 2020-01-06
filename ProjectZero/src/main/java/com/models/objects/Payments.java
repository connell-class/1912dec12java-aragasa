package com.models.objects;

import java.io.Serializable;

public class Payments implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7906123653852698566L;
	private String vin;
	private String uName;
	private float owed;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(owed);
		result = prime * result + ((uName == null) ? 0 : uName.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
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
		Payments other = (Payments) obj;
		if (Float.floatToIntBits(owed) != Float.floatToIntBits(other.owed))
			return false;
		if (uName == null) {
			if (other.uName != null)
				return false;
		} else if (!uName.equals(other.uName))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Payments [vin=" + vin + ", uName=" + uName + ", owed=" + owed + "]";
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public float getOwed() {
		return owed;
	}
	public void setOwed(float owed) {
		this.owed = owed;
	}
}
