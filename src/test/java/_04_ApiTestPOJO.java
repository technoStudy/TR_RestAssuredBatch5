import Model.Location;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.*;

public class _04_ApiTestPOJO {

    @Test
    public void extractJsonAll_POJO() {

        Location locationNesnesi=
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .extract().body().as(Location.class);
        ;

        System.out.println("locationNesnesi = " + locationNesnesi);
        System.out.println("locationNesnesi.getPlaces() = " + locationNesnesi.getPlaces());
        System.out.println("locationNesnesi.getPostcode() = " + locationNesnesi.getPostcode());

    }


}
