package org.openweathermap.api.steps;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openweather.api.model.PetPojo;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PetStoreDefs {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet";
    private String url;
    private RequestSpecification specification;
    private Response response;
    private ValidatableResponse validatableResponse;

    @Given("^Go to (.*)$")
    public void goToUrl(String endpoint) {
        url = BASE_URL + endpoint;
        specification = given().baseUri(BASE_URL).basePath(endpoint)
                .contentType(ContentType.JSON)
                .header(new Header("special-key", "grigan85"));
    }

    @When("^Add pet$")
    public void createPet() {
        PetPojo pet = new PetPojo();
        pet.setName("doggie");
        response = given().spec(specification)
                .body(pet)
                .post();
    }

    @When("^I send request with parameter (.*) equals (.*)$")
    public void findPetsByParameter(String parameter, String value) {
        response = given().spec(specification).queryParam(parameter, value).get();
    }

    @Then("^Return status (.*)$")
    public void checkStatus(int status) {
        validatableResponse = response.then();
        validatableResponse.statusCode(status);

    }

    @Then("^Return Pet with name (.*)$")
    public void checkPetName(String name) {
        PetPojo pet = validatableResponse.extract().as(PetPojo.class);
        assertThat(pet.getName(), is(name));
    }
}
