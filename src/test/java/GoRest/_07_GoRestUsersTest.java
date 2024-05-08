package GoRest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class _07_GoRestUsersTest {

    // Token ı aldım ,
    // usersCreate için neler lazım, body(user bilgileri)
    // enpoint i aldım gidiş metodu
    // BeforeClass ın içinde yapılacaklar var mı? nelerdir ?  url set ve spec hazırlanmalı

    RequestSpecification reqSpec;
    @BeforeClass
    public void Setup()
    {
        baseURI="https://gorest.co.in/public/v2/users";

        reqSpec= new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer f92bf3f56439b631d9ed928b3540968e747c8e75309c876420fb349cbb420ed1")
                .setContentType(ContentType.JSON)
                .build();
    }

    // Create User Testini yapınız




}
