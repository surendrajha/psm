package com;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.StateContoller;
import com.epayroll.web.TestRoot;

public class TestWeb extends TestRoot {

	@Autowired
	private StateContoller stateContoller;

	@SuppressWarnings("rawtypes")
	// @Test
	public void iterator_will_return_hello_world() {
		// arrange
		Iterator i = mock(Iterator.class);
		when(i.next()).thenReturn("Hello").thenReturn("World");
		// act
		String result = i.next() + " " + i.next();
		// assert
		System.out.println(result);
		Assert.assertEquals("Hello World", result);
	}

	@Test
	public void test11() throws Exception {

		request.setMethod("GET");
		request.setRequestURI("/index");

		ModelAndView mav = handlerAdapter.handle(request, response, stateContoller);

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key::" + entry.getKey() + ", Value::" + entry.getValue());
		}

		ModelAndViewAssert.assertViewName(mav, "state");
	}
}