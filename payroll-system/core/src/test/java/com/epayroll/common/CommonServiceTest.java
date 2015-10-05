package com.epayroll.common;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epayroll.TestRoot;
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsState;
import com.epayroll.service.CommonService;

/**
 * @author Surendra Jha
 */
public class CommonServiceTest extends TestRoot {

	@Autowired
	private CommonService commonService;

	@Test
	public void city() {
		System.out.println("start Business info [Test]");
		boolean testSuccess = false;
		try {
			// adding city
			UsState state = commonService.findState(3L);
			UsCity city = new UsCity();
			// city.setCityName("Chicago");
			// city.setUsState(state);
			Long id = commonService.addCity(city);
			System.out.println("City Added : " + id);

			// find city
			System.out.println("City : " + commonService.findCity(id));

			// updating city
			UsCity city2 = commonService.findCity(id);
			city2.setCityName("Los Angeles");
			city = commonService.updateCity(city2);
			System.out.println("City Updated : " + city);

			// find All Cities
			System.out.println("All Cities:");
			for (UsCity usCity : commonService.findCitites()) {
				System.out.println(usCity);
			}

			// delete city
			commonService.removeCity(city);

			testSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

}
