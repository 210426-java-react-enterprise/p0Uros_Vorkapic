package com.revature.p0.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	private LinkedList<String> sut;

	@Before
	public void setUpTest() {
		sut = new LinkedList<>();
	}

	@After
	public void tearDownTest() {
		sut = null;
	}

	@Test
	public void test_addWithNonNullValue() {
		// Arrange
		int expectedSize = 1;

		// Act
		sut.add("data");

		// Assert
		int actualSize = sut.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test(expected = Exception.class)
	public void test_addWithNullValue() {
		// Arrange
		// Nothing

		// Act
		sut.add(null);

		// Assert
		// Nothing
	}

	@Test
	public void test_pollWithEmptyList() {
		// Arrange
		// Nothing

		// Act
		String actualResult = sut.poll();

		// Assert
		Assert.assertNull(actualResult);
	}

	@Test
	public void test_pollWithPopulatedList() {
		// Arrange
		sut.add("test data 1");
		sut.add("test data 2");
		String expectedResult = "test data 1";
		int expectedSize = 1;

		// Act
		String actualResult = sut.poll();

		// Assert
		int actualSize = sut.size();
		Assert.assertEquals(expectedResult, actualResult);
		Assert.assertEquals(expectedSize, actualSize);
	}

	// TODO: (Associate task) implement this method!
	@Test
	public void test_peekWithEmptyList() {

	}

	// TODO: (Associate task) implement this method!
	@Test
	public void test_peekWithPopulatedList() {

	}

	// TODO: (Associate task) implement this method!
	@Test
	public void test_containsWithEmptyList() {

	}

	// TODO: (Associate task) implement this method!
	@Test
	public void test_containsWithPopulatedList() {

	}
}
