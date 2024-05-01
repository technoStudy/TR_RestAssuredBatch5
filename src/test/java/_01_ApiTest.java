import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class _01_ApiTest {

    @Test
    public void Test1() {
        given()
                // Hazırlık kodları buraya yazılıyor
                .when()
                // endpoint(url), metoduyla birlikte istek gönderilme aşaması

                .then()
        // assertion,test, data işlemleri
        ;
    }

    @Test
    public void statusCodeTest() {

        given()
                // gönderilecek bilgiler burada olacak
                .when()
                .get("http://api.zippopotam.us/us/90210") // post,put,delete

                .then()
                .log().body() // gelen body kısmını göster, all() dersek her şeyi gösterir
                .statusCode(200);  // test kısmı assertion status 200 mü
        ;
    }


    @Test
    public void contentTypeTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // dönen body bilgisini göster
                .statusCode(200) // dönen kod 200 mü
                .contentType(ContentType.JSON) // dönen datanın tipi JSON mı
        ;
    }

    @Test
    public void checkCountryInResponseBody() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)
                .body("country", equalTo("United States")) // country yi dışarı almadan
        // bulundu yeri (path i) vererek içerde assertion hamcrest kütüphanesi yapıyor
        ;
    }

    @Test
    public void checkCountryInResponseBody2() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu doğrulayınız

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                //.log().body()
                .body("places[0].state", equalTo("California"))
        ;
    }

    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // place dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .log().body()
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))    //places içindeki bütün place name
        ;
    }


}





