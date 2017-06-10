package com.actionForm;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookFormTest {
	BookForm book = new BookForm();

	@Test
	public void testGetAuthor() {
		book.setAuthor("test");
		assertEquals("test",book.getAuthor());
	}

	@Test
	public void testGetBarcode() {
		book.setBarcode("test");
		assertEquals("test",book.getBarcode());
	}

	@Test
	public void testGetBookName() {
		book.setBookName("wind");
		assertEquals("wind",book.getBookName());
	}

	@Test
	public void testGetBookcaseName() {
		book.setBookcaseName("novel");
		assertEquals("novel",book.getBookcaseName());
	}


}
