package foodTruckReviews;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FoodTruckController {

	@Resource
	CuisineRepository cuisineRepo;

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	FoodtruckRepository foodTruckRepo;

	@RequestMapping("/foodtruck")
	public String findOneFoodTruck(@RequestParam(value = "id") Long id, Model model) throws FoodTruckNotFoundException {
		Optional<Foodtruck> foodTruck = foodTruckRepo.findById(id);

		if (foodTruck.isPresent()) {
			model.addAttribute("foodtrucks", foodTruck.get());
			return ("foodtruck");
		}
		throw new FoodTruckNotFoundException();
	}

	

	@RequestMapping("/show-all-foodtrucks")
	public String findAllFoodTrucks(Model model) {
		model.addAttribute("foodtrucks", foodTruckRepo.findAll());
		return "show-all-foodtrucks";

	}

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id") Long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return ("review");
		}
		throw new ReviewNotFoundException();

	}

	@RequestMapping("/show-all-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("show-all-reviews");

	}

	@RequestMapping("/cuisine")
	public String findOneCuisine(@RequestParam(value = "id") Long id, Model model) throws CuisineNotFoundException {
		Optional<Cuisine> cuisine = cuisineRepo.findById(id);

		if (cuisine.isPresent()) {
			model.addAttribute("cuisines", cuisine.get());
			model.addAttribute("foodtrucks", foodTruckRepo.findByCuisinesContains(cuisine.get()));
      
			return ("cuisine");
		}
		throw new CuisineNotFoundException();

	}

	@RequestMapping("/show-all-cuisines")
	public String findAllCuisines(Model model) {
		model.addAttribute("cuisines", cuisineRepo.findAll());
		return ("show-all-cuisines");
	}
}
