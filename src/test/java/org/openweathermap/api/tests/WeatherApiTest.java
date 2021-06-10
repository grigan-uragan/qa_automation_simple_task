package org.openweathermap.api.tests;



import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class WeatherApiTest {
    private static final String URL_FIND_CITY
            = "http://api.openweathermap.org/data/2.5/find?q=Moscow&appid=bad6a5db813e0743cd1932b3fc00cc54";
    private static final String URL_GET_WEATHER
            = "http://api.openweathermap.org/data/2.5/weather?id=524901&appid=bad6a5db813e0743cd1932b3fc00cc54";

    @Test
    public void urlFindShouldReturnStatusOk() {
        when()
                .get(URL_FIND_CITY)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnResponseWithParameterMessage() {
        when()
                .get(URL_FIND_CITY)
                .then()
                .assertThat()
                .body("message", equalTo("accurate"));
    }

    @Test
    public void urlGetWeatherShouldReturnStatusOk() {
        when()
                .get(URL_GET_WEATHER)
                .then()
                .statusCode(200);
    }

    @Test
    public void urlGetWeatherShouldReturnResponseWithParameterName() {
        when()
                .get(URL_GET_WEATHER)
                .then()
                .assertThat()
                .body("name", equalTo("Moscow"));
    }

}
