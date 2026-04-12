package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    static void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getBaseUri())
                .setContentType("application/json")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType("application/json")
                .expectResponseTime(lessThan(5000L))
                .build();
    }
}