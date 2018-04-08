package com.dsd26.bachkhoaxanh.business;

/*
 * author: Nguyễn Phúc Đạc
 */

public class Point implements Cloneable {
	public int x;
	public int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point clone() {
		try {
			return (Point) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Cloning not allowed.");
			return this;
		}
	}
	
}
