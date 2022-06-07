package demoqa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class TextBoxTests extends TestBase {


    @Test
    @DisplayName("Successful fill registration test")
    void fillFormTest() {
        String firstName = "first";
        String lastName = "last";
        String email = "mail@mail.com";
        String gender = "Female";
        String mobile = "7778889911";
        String date = "29 November,1992";
        Integer month = 10;
        Integer year = 92;
        String subjects = "English";
        String hobbie1 = "Reading";
        String hobbie2 = "Music";
        String currentAddress = "address";
        String state = "NCR";
        String city = "Delhi";
        String image = "catImage.jpeg";

        step("Open registration form", ()->{
            open("/automation-practice-form");
            Selenide.executeJavaScript("document.getElementById('fixedban').hidden = 'true'");
            executeJavaScript("$('footer').remove()");
        });

        step("Fill registration form", ()->{
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").setValue(mobile);
            $("#subjectsInput").setValue(subjects).pressEnter();
            $("#hobbiesWrapper").$(byText(hobbie1)).click();
            $("#hobbiesWrapper").$(byText(hobbie2)).click();
            $("#currentAddress").setValue(currentAddress);
            $("#uploadPicture").uploadFromClasspath(image);
            $("#react-select-3-input").setValue(state).pressEnter();
            $("#react-select-4-input").setValue(city).pressEnter();
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(month);
            $(".react-datepicker__year-select").selectOption(year);
            $(by("aria-label", "Choose Sunday, November 29th, 1992")).click();
            $("#submit").click();
        });

        step("Verify form data", ()->{
            $(".table-responsive").shouldHave(text("Student Name	" + firstName + " " + lastName),
                    text("Mobile " + mobile), text("Picture	" + image), text("Student Email " + email),
                    text("Gender Female"), text("Date of Birth " + date), text("Subjects " + subjects),
                    text("Hobbies Reading, Music"), text("Address " + currentAddress), text("State and City " + state + " " + city));
        });
    }
}

