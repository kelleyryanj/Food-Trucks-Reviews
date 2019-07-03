package foodTruckReviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class FoodtruckPopulator implements CommandLineRunner{
	@Resource
	private FoodtruckRepository foodtruckRepo;
	
	@Resource
	private CuisineRepository cuisineRepo;
	
	@Resource
	private ReviewRepository reviewRepo;

	@Override
	public void run(String... args) throws Exception {
		
		Cuisine mexican = cuisineRepo.save(new Cuisine("Mexican"));
		Cuisine italian = cuisineRepo.save(new Cuisine("Italian"));
		Cuisine american = cuisineRepo.save(new Cuisine("American"));
		Cuisine mediterranean = cuisineRepo.save(new Cuisine("Mediterranean"));
		
		
		Foodtruck mikeys = foodtruckRepo.save(new Foodtruck("Mikey's Late Night Slice", "mikeys", american, italian));
		Foodtruck kabob = foodtruckRepo.save(new Foodtruck("Kabob Time", "kabob", mediterranean));
		Foodtruck rays = foodtruckRepo.save(new Foodtruck("Ray Rays", "rayrays", american));
		Foodtruck halal = foodtruckRepo.save(new Foodtruck("Halal New York Gyro", "halal", mediterranean));
		Foodtruck arepa = foodtruckRepo.save(new Foodtruck("La Arepa Picante", "arepa", mexican));
		Foodtruck mrgrill = foodtruckRepo.save(new Foodtruck("Mr. Grill Tacos", "mrtaco", mexican));
		

		reviewRepo.save(new Review("Mikey's Review 1", mikeys));
		reviewRepo.save(new Review("Mikey's Review 2", mikeys));
		reviewRepo.save(new Review("Kabob Time Review", kabob));
		reviewRepo.save(new Review("Ray Rays Review 1", rays));
		reviewRepo.save(new Review("Ray Rays Review 2", rays));
		reviewRepo.save(new Review("Halal New York Gyro Review", halal));
		reviewRepo.save(new Review("La Arepa Picante Review", arepa));
		reviewRepo.save(new Review("Mr. Grill Tacos Review", mrgrill));
	}

}
