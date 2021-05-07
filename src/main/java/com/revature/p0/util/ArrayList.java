package com.revature.p0.util;

//import java.lang.reflect.Array;

import java.util.Arrays;

public class ArrayList<T> implements List<T>{

	private final int INITIAL_SIZE = 10;
	private int currentSize;
	private int numElements;
	private Object[] array;

	/**
	 * Constructor
	 */
	public ArrayList() {
		super();
		this.currentSize = INITIAL_SIZE;
		array = new Object[INITIAL_SIZE];
		numElements = 0;
	}

	/**
	 * Adds data to first empty value in array
	 * @param data element being added to ArrayList
	 */
	@Override
	public void add(T data) {
		if (numElements == currentSize) {
			doubleArray();
		}
		array[numElements++] = data;
	}

	/**
	 * Adds data to existing index, replacing contents of old index
	 * @param index ArrayList index where data will be added
	 * @param data element being added to ArrayList
	 */
	public void add(int index, T data) {
		withinBounds(index); // checks if index is valid; throws exception if not
		array[index] = data;
		numElements++;
	}

	/**
	 * UNCHECKED CAST
	 * Returns data from last index of ArrayList and deletes its contents
	 * @return value of element in last position of ArrayList
	 */
	@Override
	public T pop() {
		T data =(T) array[numElements];
		array[numElements] = null;
		numElements--;

		return data;
	}

	/**
	 * UNCHECKED CAST
	 * Returns data from ArrayList at index value
	 * @param index index data is pulled from
	 * @return value of element in ArrayList at index value
	 */
	@Override
	public T get(int index)  {
		withinBounds(index); // checks if index is valid; throws exception if not
		return (T) array[index];

	}

	/**
	 * UNCHECKED CAST
	 * Searches ArrayList for contents matching select data
	 * @param data to check with contents of array
	 * @return true if contents exist in array; false otherwise
	 */
	@Override
	public boolean contains(T data) {
		for (int i = 0; i < numElements; i++) {
			if (data.equals((T) array[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return current size of ArrayList
	 */
	@Override
	public int size() {
		return numElements;
	}

	/**
	 * Doubles the size of the array. Invoked only if array is full
	 */
	private void doubleArray() {
		currentSize *= 2;
		array = Arrays.copyOf(array, currentSize);
	}

	/**
	 * Checks whether an index is within bounds of current number of elements in ArrayList
	 * @param index index being checked
	 * @throws IndexOutOfBoundsException if index is bigger than the number of elements in ArrayList or less than zero
	 */
	private void withinBounds(int index) throws IndexOutOfBoundsException {
		if (index >= numElements || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}
}
