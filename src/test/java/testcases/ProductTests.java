package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.testng.annotations.Test;

import java.util.List;

import io.restassured.response.Response;
import routes.Routes;
public class ProductTests extends BaseClass {

	
	
	@Test
	public void testGetAllProducts() {
		
		given()
		.when()
		.get(Routes.GET_ALL_PRODUCTS)
		.then()
		.statusCode(200)
		.body("size()",greaterThan(0));
	}
	@Test
	public void testGetSingleProductId() {
		int productId= configReader.getIntProperty("productId");
		
		given()
		.pathParam("id", productId)
		.when()
		.get(Routes.GET_PRODUCT_BY_ID)
		.then()
		.statusCode(200)
		.log().body();
	}
	@Test
	public void testGetLimitedProducts() {
		
		given()
		.pathParam("limit", 3)
		.when()
		.get(Routes.GET_PRODUCTS_WITH_LIMIT)
		.then()
		.statusCode(200)
		.body("size()", equalTo(3));
	}
	
	//GET_PRODUCTS_SORTED
	@Test
	public void testGetSortedProduct() {
		Response response=given()
		.pathParam("order", "desc")
		.when()
		.get(Routes.GET_PRODUCTS_SORTED)
		.then()
		.statusCode(200)
		.extract().response();
		
		List<Integer>productId=response.jsonPath().getList(null);
	}
}

