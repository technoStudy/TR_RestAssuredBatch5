import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _02_ApiTest {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    @BeforeClass
    public void Setup()
    {
        baseURI="https://gorest.co.in/public/v1";   // hazırda tanımlanmış RESTASSURED ait değişken

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.BODY)
                .expectContentType(ContentType.JSON)
                .build();
    }


    @Test
    public void Test1(){
        given()
                // request özellikleri
                .spec(requestSpec)
                .when()
                .get("/users")// başında http yok ise baseURI başına eklenir
                // https://gorest.co.in/public/v1/users

                .then()
                // test özellikleri , response özellikleri
                .spec(responseSpec)
                ;
    }






}
