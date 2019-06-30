package foodTruckReviews;

import javax.annotation.Resource;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Collection;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import foodTruckReviews.Cuisine;
import foodTruckReviews.CuisineRepository;
import foodTruckReviews.Review;
import foodTruckReviews.ReviewRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest

public class JPAMappingsTest {
	@Resource
	private CuisineRepository cuisineRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TestEntityManager entityManager;
	
	
	@Test
	public void shouldSaveAndLoadCuisine() {
		Cuisine cuisine = new Cuisine("type");
		cuisine = cuisineRepo.save(cuisine);
		long cuisineId = cuisine.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Cuisine> result = cuisineRepo.findById(cuisineId);
		cuisine = result.get();
		assertThat(cuisine.getType(), is("type"));
		
	}
	
	@Test
	public void shouldGenerateCuisineId() {
		Cuisine cuisine = new Cuisine("type");
		cuisine = cuisineRepo.save(cuisine);
		long cuisineId = cuisine.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(cuisineId, is(greaterThan(0L)));
		
	}

	@Test
	public void shouldSaveAndLoadReview() {
		Review review = new Review("name", "map");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("name"));
		
	}
	
	@Test
	public void shouldEstablishReviewToCuisinesRelationships() {
		Cuisine american = new Cuisine("american");
		american = cuisineRepo.save(american);
		Cuisine italian = new Cuisine("italian");
		italian = cuisineRepo.save(italian);
		
		Review review = new Review("Mikey's Late Night Slice", "map1", italian, american);
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getCuisines(), containsInAnyOrder(american, italian));
	}
	
	@Test
	public void shouldFindReviewsForCuisine() {
		Cuisine mexican = cuisineRepo.save(new Cuisine("mexican"));

		Review review1 = reviewRepo.save(new Review("Mr. Grill Tacos", "map1", mexican));
		Review review2 = reviewRepo.save(new Review("La Arepa Picante", "map2", mexican ));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCuisine = reviewRepo.findByCuisinesContains(mexican);
		assertThat(reviewsForCuisine, containsInAnyOrder(review1, review2));
		
	}
	
	@Test
	public void shouldFindReviewsForCuisineId() {
		Cuisine mediterranean  = cuisineRepo.save(new Cuisine("mediterranean"));
		long cuisineId = mediterranean.getId();
		
		Review review3 = reviewRepo.save(new Review("Halal Ney YOrk Gyro", "map3", mediterranean));
		Review review4 = reviewRepo.save(new Review("Kabob Time", "map4", mediterranean));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCuisine = reviewRepo.findByCuisinesId(cuisineId);
		assertThat(reviewsForCuisine, containsInAnyOrder(review3, review4));
		
		
	}
}

