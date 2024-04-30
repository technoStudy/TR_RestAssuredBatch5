import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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

}











