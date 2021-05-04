package com.revature.p0.util;

//import java.lang.reflect.Array;

public class ArrayList<T> implements List<T>{

	private final int INITIAL_SIZE = 10;
	private int currentSize;
	private Object[] array;


	public ArrayList() {
		this.currentSize = INITIAL_SIZE;
		array = new Object[INITIAL_SIZE];
	}

	@Override
	public void add(T data) {

	}

	public void add(int index, T data) {

	}

	@Override
	public T pop() {
		return null;
	}

	@Override
	public T get(int index) {
		return null;
	}

	@Override
	public boolean contains(T data) {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}
}
