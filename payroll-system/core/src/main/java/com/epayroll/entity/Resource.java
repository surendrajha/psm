package com.epayroll.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class Resource implements Serializable {

	private Calendar creationDate;
	private Person creator;
	private Person modifier;
	private Calendar updationDate;

	public Resource() {
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	@ManyToOne(optional = true)
	public Person getCreator() {
		return creator;
	}

	@ManyToOne(optional = true)
	public Person getModifier() {
		return modifier;
	}

	public Calendar getUpdationDate() {
		return updationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public void setModifier(Person modifier) {
		this.modifier = modifier;
	}

	public void setUpdationDate(Calendar updationDate) {
		this.updationDate = updationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((modifier == null) ? 0 : modifier.hashCode());
		result = prime * result + ((updationDate == null) ? 0 : updationDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Resource other = (Resource) obj;
		if (creationDate == null) {
			if (other.creationDate != null) {
				return false;
			}
		} else if (!creationDate.equals(other.creationDate)) {
			return false;
		}
		if (creator == null) {
			if (other.creator != null) {
				return false;
			}
		} else if (!creator.equals(other.creator)) {
			return false;
		}
		if (modifier == null) {
			if (other.modifier != null) {
				return false;
			}
		} else if (!modifier.equals(other.modifier)) {
			return false;
		}
		if (updationDate == null) {
			if (other.updationDate != null) {
				return false;
			}
		} else if (!updationDate.equals(other.updationDate)) {
			return false;
		}
		return true;
	}

}
