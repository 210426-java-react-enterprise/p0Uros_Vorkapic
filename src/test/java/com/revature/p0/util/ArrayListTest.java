package com.revature.p0.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

	private ArrayList<String> sut;

	@Before
	public void setUpTest() {
		sut = new ArrayList<>();
	}

	@After
	public void tearDownTest() {
		sut = null;
	}

	@Test
	public void test_addWithValidData() {
		// Arrange
		int expectedSize = 1;

		// Act
		sut.add("data");

		// Assert
		int actualSize = sut.size();
		Assert.assertEquals(expectedSize, actualSize);

	}

	@Test (expected = Exception.class)
	public void test_addWithInvalidData() {
		sut.add(null);
	}

	@Test
	public void test_addWithValidIndex() {
		int expectedSize = 2;

		sut.add("data");
		sut.add(0, "data");

		int actualSize = sut.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test (expected = Exception.class)
	public void test_addWithInvalidIndex() {
		sut.add(1, "data");
	}

	@Test
	public void test_popWithNonEmptyList() {
		int expectedSize = 1;
		sut.add("data");
		sut.add("data");

		sut.pop();

		int actualSize = sut.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test (expected = Exception.class)
	public void test_popWithEmptyList() {
		sut.pop();
	}

	@Test
	public void test_getWithValidIndex() {
		sut.add("data");

		sut.get(0);

		Assert.assertEquals(sut.get(0), "data");
	}

	@Test (expected = Exception.class)
	public void test_getWithInvalidIndex() {
		sut.get(1);
	}

	@Test
	public void test_containsWithNotNullValue() {
		boolean expectedContains = true;
		sut.add("data");

		boolean actuallyContains = sut.contains("data");

		Assert.assertEquals(expectedContains, actuallyContains);
	}

}
