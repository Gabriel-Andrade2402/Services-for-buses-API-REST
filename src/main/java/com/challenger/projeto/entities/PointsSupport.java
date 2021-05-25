package com.challenger.projeto.entities;

import java.io.Serializable;

public class PointsSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String point1;
	private String point2;
	private String point3;
	private String point4;
	private String point5;
	public PointsSupport(String point1, String point2, String point3, String point4, String point5) {
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.point4 = point4;
		this.point5 = point5;
	}
	public PointsSupport() {
	}
	public String getPoint1() {
		return point1;
	}
	public void setPoint1(String point1) {
		this.point1 = point1;
	}
	public String getPoint2() {
		return point2;
	}
	public void setPoint2(String point2) {
		this.point2 = point2;
	}
	public String getPoint3() {
		return point3;
	}
	public void setPoint3(String point3) {
		this.point3 = point3;
	}
	public String getPoint4() {
		return point4;
	}
	public void setPoint4(String point4) {
		this.point4 = point4;
	}
	public String getPoint5() {
		return point5;
	}
	public void setPoint5(String point5) {
		this.point5 = point5;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((point1 == null) ? 0 : point1.hashCode());
		result = prime * result + ((point2 == null) ? 0 : point2.hashCode());
		result = prime * result + ((point3 == null) ? 0 : point3.hashCode());
		result = prime * result + ((point4 == null) ? 0 : point4.hashCode());
		result = prime * result + ((point5 == null) ? 0 : point5.hashCode());
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
		PointsSupport other = (PointsSupport) obj;
		if (point1 == null) {
			if (other.point1 != null)
				return false;
		} else if (!point1.equals(other.point1))
			return false;
		if (point2 == null) {
			if (other.point2 != null)
				return false;
		} else if (!point2.equals(other.point2))
			return false;
		if (point3 == null) {
			if (other.point3 != null)
				return false;
		} else if (!point3.equals(other.point3))
			return false;
		if (point4 == null) {
			if (other.point4 != null)
				return false;
		} else if (!point4.equals(other.point4))
			return false;
		if (point5 == null) {
			if (other.point5 != null)
				return false;
		} else if (!point5.equals(other.point5))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[\npoint1=" + point1 + "\npoint2=" + point2 + "\npoint3=" + point3 + "\npoint4=" + point4
				+ "\npoint5=" + point5 + "]";
	}
	

	
	
	
}
