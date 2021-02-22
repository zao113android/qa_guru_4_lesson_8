package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.FormPage;

public class FillFormPageObjectsTest extends TestBase {

    FormPage formPage = new FormPage();

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            name = firstName + " " + lastName,
            gender =  "Other",
            email = faker.internet().emailAddress(),
            state = "NCR",
            city = "Delhi",
            location = state + " " + city,
            number = faker.number().digits(10),
            subject = "English",
            address = faker.address().fullAddress(),
            month = "January",
            year = faker.number().numberBetween(1900, 2100) + "",
            day = faker.number().numberBetween(1, 27) + "",
            hobby = "Reading",
            picture = "file.png";

    @Test
    public void fillFormPageTest() {
        formPage.openForm();
        formPage.fillUserInfo(firstName, lastName, gender, email, number);
        formPage.fillDayOfBirth(month, year, day);
        formPage.fillAdditionalInfo(state, city, subject, address, hobby);
        formPage.uploadPicture(picture);
        formPage.submitForm();
        formPage.check(name, gender, email, number,
                month, year, day,
                location, subject, address, hobby, picture);
    }
}
