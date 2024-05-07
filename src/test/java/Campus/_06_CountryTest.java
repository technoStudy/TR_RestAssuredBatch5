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
    Faker randomUreteci = new Faker();
    String countryName = "";
    String countryCode = "";

    @BeforeClass
    public void LoginCampus() {
        baseURI = "https://test.mersys.io";
        //token cookies içinde geldi
        // cookies i alıcam
        // request spec hazırlayacağım
        // environment varible : baseURI
        //{"username": "turkeyts", "password": "TechnoStudy123","rememberMe": "true"}
        String userCredential = "{\"username\": \"turkeyts\", \"password\": \"TechnoStudy123\",\"rememberMe\": \"true\"}";

        Map<String, String> userCredMap = new HashMap<>();
        userCredMap.put("username", "turkeyts");
        userCredMap.put("password", "TechnoStudy123");
        userCredMap.put("rememberMe", "true");

        Cookies gelenCookies =
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

        reqSpec = new RequestSpecBuilder()
                .addCookies(gelenCookies)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void CreateCountry() {
        //burada gelen tooken ın yine cookies içinde geri gitmesi lazım :spec
        countryName = randomUreteci.address().country() + randomUreteci.address().countryCode()
                      + randomUreteci.address().latitude();
        countryCode = randomUreteci.address().countryCode();

        Map<String, String> newCountry = new HashMap<>();
        newCountry.put("name", countryName);
        newCountry.put("code", countryCode);

        //Not: Spec bilgileri given dan hemen sonra yazılmalı!

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

    @Test(dependsOnMethods = "CreateCountry")
    public void CreateCountryNegative() {
        // Yukarıda gönderilen name ve codu tekrar göndererek kayıt yapılamadığını doğrulayınız.
        //burada gelen tooken ın yine cookies içinde geri gitmesi lazım:spec

        Map<String, String> reNewCountry = new HashMap<>();
        reNewCountry.put("name", countryName);
        reNewCountry.put("code", countryCode);

        given()
                .spec(reqSpec)
                .body(reNewCountry)
                .when()
                .post("/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(400)
        ;
    }

    @Test
    public void UpdateCountry() {
        // yukarıda create edilen ülkenin adını bir başka ülke adı ile update ediniz.





    }

}
