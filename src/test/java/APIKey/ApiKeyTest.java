package APIKey;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

//endpoint: https://l9njuzrhf3.execute-api.eu-west-1.amazonaws.com/prod/user
//        ApiKey in Key ve Values i  vardır
//        Key : x-api-key
//        Values: GwMco9Tpstd5vbzBzlzW9I7hr6E1D7w2zEIrhOra

public class ApiKeyTest {

    @Test
    public void ApiKeyTesting()
    {
        given()
                //.header("Authorization", "Bearer f92bf3f56439b631d9ed928b3540968e747c8e75309c876420fb349cbb420ed1")
                .header("x-api-key", "GwMco9Tpstd5vbzBzlzW9I7hr6E1D7w2zEIrhOra")
                .when()
                .get("https://l9njuzrhf3.execute-api.eu-west-1.amazonaws.com/prod/user")

                .then()
                .log().body()
        ;


    }

    // google developer weather api key
    // https://developers.google.com/maps/documentation/geocoding/get-api-key
    // postmande query parameter istirosa Api key seçildikten sonra Eklenme türü seçilebilir.
    // header veya query parameter hali
    // hangisi isteniyorsa ona göre yapılıyor

}
