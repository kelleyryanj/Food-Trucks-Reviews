package foodTruckReviews;

import java.util.Optional;

import javax.annotation.Resource;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodTruckController.class)
public class MockMvcControllerTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private FoodtruckRepository foodTruckRepo;
	
	@MockBean
	private CuisineRepository cuisineRepo;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	
	@Mock
	Foodtruck foodTruck;
	
	
	@Test
	public void shouldRouteToSingleFoodTruckView() throws Exception{
		long arbitraryFoodTruckId = 1;
		when(foodTruckRepo.findById(arbitraryFoodTruckId)).thenReturn(Optional.of(foodTruck));
		
		mvc.perform(get("/foodtruck?id=1")).andExpect(view().name(is("foodtruck")));
	}
	
	@Test
	public void shouldRouteToSingleFoodTruck() throws Exception{
		long arbitraryFoodTruckId = 1;
		when(foodTruckRepo.findById(arbitraryFoodTruckId)).thenReturn(Optional.of(foodTruck));
		
		mvc.perform(get("/foodtruck?id=1")).andExpect(status().isOk());
		
		}
	
	@Test
	public void shouldNotRouteToSingleFoodTruck() throws Exception {
		mvc.perform(get("/foodtruck?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleFoodTruckIntoModel() throws Exception {
		when(foodTruckRepo.findById(1L)).thenReturn(Optional.of(foodTruck));
		mvc.perform(get("/foodtruck?id=1")).andExpect(model().attribute("foodtrucks", is (foodTruck)));
	}
}
