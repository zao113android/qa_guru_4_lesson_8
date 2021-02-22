package pages;

import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class FormPage {

    @Step("Open form")
    public void openForm() {
        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
    }

    @Step("Fill user basic info")
    public void fillUserInfo(String firstName, String lastName, String gender, String email, String number) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(number);
    }

    @Step("Fill day of birth")
    public void fillDayOfBirth(String month, String year, String day) {
        $("#dateOfBirthInput").click();
        $((".react-datepicker__month-select")).selectOption(month);
        $((".react-datepicker__year-select")).selectOption(year);
        $((".react-datepicker__day--0" + day)).click();
    }

    @Step("Fill additional info")
    public void fillAdditionalInfo(String state, String city, String subject, String address, String hobby) {
        $("#subjectsInput").setValue(subject);
        $(".subjects-auto-complete__menu-list").$(byText(subject)).click();
        $(byText(hobby)).click();
        $("#currentAddress").setValue(address);
        $("#state").scrollTo();
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
    }

    @Step("Upload picture")
    public void uploadPicture(String picture) {
        $("#uploadPicture").uploadFile(new File("./src/test/resources/img/" + picture));
    }

    @Step("Submit the form")
    public void submitForm() {

        // submit the form
        $("#submit").click();
    }

    @Step("Check the results")
    public void check(String name, String gender, String email, String number,
                      String month, String year, String day, String location, String subject,
                      String address, String hobby, String picture) {
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
