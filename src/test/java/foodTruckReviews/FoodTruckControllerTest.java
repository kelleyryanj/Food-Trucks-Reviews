package foodTruckReviews;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;







public class FoodTruckControllerTest {
	
	@InjectMocks
	FoodTruckController underTest;
	
	@Mock
	private Foodtruck foodTruck;
	
	@Mock
	private Foodtruck anotherFoodtruck;
	
	@Mock
	private FoodtruckRepository foodTruckRepo;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private Cuisine cuisine;
	
	@Mock
	private Cuisine anotherCuisine;
	
	@Mock
	private CuisineRepository cuisineRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleFoodTruckToModel() throws FoodTruckNotFoundException {
		long arbitraryFoodTruckId = 1;
		when(foodTruckRepo.findById(arbitraryFoodTruckId)).thenReturn(Optional.of(foodTruck));
		
		underTest.findOneFoodTruck(arbitraryFoodTruckId, model);
		verify(model).addAttribute("foodtrucks", foodTruck);
	}
	
	@Test
	public void shouldAddAllFoodTrucksToModel() {
		Collection<Foodtruck> allFoodTrucks = Arrays.asList(foodTruck, anotherFoodtruck);
		when(foodTruckRepo.findAll()).thenReturn(allFoodTrucks);
		
		underTest.findAllFoodTrucks(model);
		verify(model).addAttribute("foodtrucks", allFoodTrucks);
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		
		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("reviews", review);
		
		
	}
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allreviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allreviews);
		
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allreviews);
	}
}

