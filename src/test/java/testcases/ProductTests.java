package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Playload;
import pojo.Product;
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
	public void testGetSortedProductDescending() {
		Response response=given()
		.pathParam("order", "desc")
		.when()
		.get(Routes.GET_PRODUCTS_SORTED)
		.then()
		.statusCode(200)
		.extract().response();
		
		List<Integer>productIds=response.jsonPath().getList("id", Integer.class);
	 	 assertThat(isSortedDescending(productIds), is(true));
		
	}
	@Test
	public void testGetSortedProductAscending() {
		Response response=given()
		.pathParam("order", "asc")
		.when()
		.get(Routes.GET_PRODUCTS_SORTED)
		.then()
		.statusCode(200)
		.extract().response();	
		List<Integer>productIds=response.jsonPath().getList("id", Integer.class);
	 	 assertThat(isAscending(productIds), is(true));
		
	}
	
	@Test
	public void testGetAllProductCategory() {
		
		given()
		.when()
		.get(Routes.GET_ALL_PRODUCTS)
		.then()
		.statusCode(200)
		.body("size()",greaterThan(0));
		
	}
	@Test
public void testGetProductByCategory() {
		
		given()
		.pathParam("category", "electronics")
		.when()
		.get(Routes.GET_PRODUCTS_BY_CATEGORY)
		.then()
		.statusCode(200)
		.body("size()", greaterThan(0))
		.body("category", everyItem(notNullValue()))
		.body("category",everyItem(equalTo("electronics")))
		.log().body();
	}

	@Test
	public void testCreateProduct() {
		
	Product newProduct=	Playload.productplayload()	;
		
	int productId=	given()
		.contentType(ContentType.JSON)
		.body(newProduct)
		.when()
		.post(Routes.CREATE_PRODUCT)
		.then()
		.log().body()
		.extract().jsonPath().getInt("id");
		
		System.out.println(productId);
		
	}


	
	//@Test
	public void testUpdatetheRecord() {
		
int productid=	configReader.getIntProperty("productId");
		
		given()
		
		.when()
		
		.then()
		.statusCode(200);
		
	}
}