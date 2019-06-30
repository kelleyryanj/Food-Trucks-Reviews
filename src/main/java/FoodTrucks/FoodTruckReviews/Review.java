package FoodTrucks.FoodTruckReviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity

public class Review {
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String map;
	
	@ManyToMany
	private Collection<Cuisine> cuisines;

	public Review() {
		
	}
	
	public Review(String name, String map, Cuisine...cuisines) {
		this.name = name;
		this.map = map;
		this.cuisines = new HashSet<>(Arrays.asList(cuisines));
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMap() {
		return map;
	}
	
	public Collection<Cuisine> getCuisines(){
		return cuisines;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
