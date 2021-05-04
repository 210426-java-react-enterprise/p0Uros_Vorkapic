package com.revature.p0.util;

public interface List<T> {

	void add(T data);
	T pop();
	T get(int index);
	boolean contains(T data);
	int size();
}
