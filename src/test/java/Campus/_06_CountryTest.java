package Campus;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class _06_CountryTest {

    RequestSpecification reqSpec;
    Faker randomUreteci=new Faker();

    @BeforeClass
    public void LoginCampus(){
        baseURI="https://test.mersys.io";
         //token cookies içinde geldi
        // cookies i alıcam
        // request spec hazırlayacağım
        // environment varible : baseURI
        //{"username": "turkeyts", "password": "TechnoStudy123","rememberMe": "true"}
        String userCredential="{\"username\": \"turkeyts\", \"password\": \"TechnoStudy123\",\"rememberMe\": \"true\"}";

        Map<String,String> userCredMap=new HashMap<>();
        userCredMap.put("username","turkeyts");
        userCredMap.put("password","TechnoStudy123");
        userCredMap.put("rememberMe","true");

        Cookies gelenCookies=
        given()
                //.body(userCredential)
                .body(userCredMap)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/login")
                .then()
                //.log().all()
                .statusCode(200)
                .extract().response().getDetailedCookies();
        ;

        //System.out.println("gelenCookies = " + gelenCookies);

        reqSpec= new RequestSpecBuilder()
                .addCookies(gelenCookies)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void CreateCountry(){
         //burada gelen tooken ın yine cookies içinde geri gitmesi lazım :spec
        Map<String,String> newCountry=new HashMap<>();
        newCountry.put("name",randomUreteci.address().country()+randomUreteci.address().countryCode());
        newCountry.put("code",randomUreteci.address().countryCode());

        //Not: Spec bilgileri givendan hemen sonra yazılmalı!

        given()
                .spec(reqSpec)  // gelen cookies, yeni istek için login olduğumun kanıtı olarak gönderildi.
                .body(newCountry)
                .when()
                .post("/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(201)
        ;

    }

    @Test
    public void CreateCountryNegative(){
        //burada gelen tooken ın yine cookies içinde geri gitmesi lazım:spec

    }

    @Test
    public void UpdateCountry(){
        //burada gelen tooken ın yine cookies içinde geri gitmesi lazım:spec

    }

}
