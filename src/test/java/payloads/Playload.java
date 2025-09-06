package payloads;

import com.github.javafaker.Faker;

import java.util.Random;

import pojo.Product;

public class Playload {
	private static final Faker faker = new Faker();
	private static final String categories[] = { "electronics", "furniture", "mobile", "clothing", "books" };

	private static final Random random = new Random();

 public static	Product Playload() {

		String name = faker.commerce().productName();
		double price = Double.parseDouble(faker.commerce().price());
		String description = faker.lorem().sentence();
		String image = "https://i.pravatar.cc/100";
		String category = categories[random.nextInt(categories.length)];
		
	    new Product(name, price, description, image, category);
		return new Product(name, price, description, image, category);

	}

}
