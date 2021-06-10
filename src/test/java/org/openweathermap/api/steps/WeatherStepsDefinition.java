package org.openweathermap.api.steps;

import com.jayway.restassured.response.Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class WeatherStepsDefinition {
    private String url;
    private Response response;


    @Given("^I create url for search (.*)$")
    public void createUrl(String param) {
        url = String.format(
                "http://api.openweathermap.org/data/2.5/find?" +
                        "q=%s&appid=bad6a5db813e0743cd1932b3fc00cc54", param);
    }

    @Given("^I create url for get weather in Moscow$")
    public void getUrlWeatherInMoscow() {
        url = "http://api.openweathermap.org/data/2.5/weather?" +
                "id=524901&units=metric&appid=bad6a5db813e0743cd1932b3fc00cc54";
    }

    @When("^I send request$")
    public void sendRequest() {
        response = when().get(url);
    }

    @Then("^I get response with status ok$")
    public void checkStatus() {
        response.then().statusCode(200);
    }

    @And("^I get response with parameter (.*) equal (.*)$")
    public void checkParameter(String param, String expected) {
        response.then().assertThat().body(param, equalTo(expected));
    }
}
