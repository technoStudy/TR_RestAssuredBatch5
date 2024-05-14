package GoRest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _08_GoRestCommentTest {

//     {
//          "id": 95069,
//             "post_id": 122490,
//             "name": "Malti Kaur",
//             "email": "kaur_malti@strosin-keebler.example",
//             "body": "In ut vel."
//     }


     Faker randomUreteci=new Faker();
     RequestSpecification reqSpec;
     int commentID=0;


     @BeforeClass
     public void Setup()
     {
          baseURI="https://gorest.co.in/public/v2/comments";

          reqSpec= new RequestSpecBuilder()
                  .addHeader("Authorization", "Bearer f92bf3f56439b631d9ed928b3540968e747c8e75309c876420fb349cbb420ed1")
                  .setContentType(ContentType.JSON)
                  .build();
     }

    // Soru : CreateComment testini yapınız

     @Test
     public void CreateComment(){
          String fullName=randomUreteci.name().fullName();
          String email=randomUreteci.internet().emailAddress();
          String body=randomUreteci.lorem().paragraph();
          String postId="122490";

          Map<String,String> newComment=new HashMap<>();
          newComment.put("name",fullName);
          newComment.put("email",email);
          newComment.put("body",body);
          newComment.put("post_id",postId);

          commentID=
          given()
                  .spec(reqSpec)
                  .body(newComment)

                  .when()
                  .post("")

                  .then()
                  .log().body()
                  .statusCode(201)
                  .extract().path("id")
          ;

          System.out.println("commentID = " + commentID);
     }

     // Soru : Create edilen Comment ı GetCommentByID testi çağırarak id sinin kontrolünü yapınız.

     @Test(dependsOnMethods = "CreateComment")
     public void GetCommentByID(){

          given()
                  .spec(reqSpec)
                  .when()
                  .get(""+commentID)

                  .then()
                  .log().body()
                  .body("id",equalTo(commentID))
                  ;
     }

     // Soru : Create edilen comment ın name ini güncelleyiniz.

     @Test(dependsOnMethods = "GetCommentByID")
     public void UpdateComment()
     {
          String updName="İsmet_"+randomUreteci.name().fullName();
          Map<String,String> updComment=new HashMap<>();
          updComment.put("name",updName);

          given()
                  .spec(reqSpec)
                  .body(updComment)
                  .when()
                  .put(""+commentID)

                  .then()
                  .log().body()
                  .body("name", equalTo(updName))
          ;
     }




}
