package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FillFormWithStepsTest extends TestBase {

    @Test
    @Tag("positive")
    @DisplayName("Fill form positive test")
    void confirmationIsDispplayedTest() {
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
                day = faker.number().numberBetween(1, 29) + "",
                hobby = "Reading",
                picture = "file.png";

        // open form
        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        // fill form
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(number);
        $("#dateOfBirthInput").click();
        $((".react-datepicker__month-select")).selectOption(month);
        $((".react-datepicker__year-select")).selectOption(year);
        $((".react-datepicker__day--0" + day)).click();
        $("#subjectsInput").setValue(subject);
        $(".subjects-auto-complete__menu-list").$(byText(subject)).click();
        $(byText(hobby)).click();
       // $("#uploadPicture").uploadFromClasspath("src/test/resources/img/" + picture);
        $("#uploadPicture").uploadFile(new File("./src/test/resources/img/" + picture));
        $("#currentAddress").setValue(address);
        $("#state").scrollTo();
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        // submit the form
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        // quick check of filled info
        // also wanna change to field - value validation
        $(byClassName("table-responsive")).shouldHave(text(name), text(location), text(email));

        // and the better checker
        $x("//td[text()='Student Name']").parent().shouldHave(text(name));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(number));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(day + " " + month + "," + year));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subject));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby));
        $x("//td[text()='Picture']").parent().shouldHave(text(picture));
        $x("//td[text()='Address']").parent().shouldHave(text(address));
        $x("//td[text()='State and City']").parent().shouldHave(text(location));
    }
}
