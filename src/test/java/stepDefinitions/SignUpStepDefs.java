package stepDefinitions;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignUpPage;
import utilities.Driver;
import utilities.ReusableMethods;
import java.time.Duration;


public class SignUpStepDefs {

    AndroidDriver driver= Driver.getAndroidDriver();
    SignUpPage signpage=new SignUpPage();

    @Given("The Hiwell application is launched")
    public void the_hiwell_application_is_launched() {
        System.out.println("Driver Instance: " + driver);
    }

    @When("The user taps on the {string} button")
    public void the_user_taps_on_the_button(String signUp) throws InterruptedException {
        signpage.signUpButton.click();
    }

    @When("The user selects the {string} therapy language")
    public void the_user_selects_the_therapy_language(String languageOption) {
        ReusableMethods.wait(2);
        ReusableMethods.scrollWithUiScrollableAndClick(languageOption);

        System.out.println("Başarılı bir şekilde dil seçildi: " + languageOption + " (UiAutomator ile)");
    }

    @Then("The user should be greeted with {string} message")
    public void the_user_should_be_greeted_with_message(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), 3);
        WebElement visibleWelcomeText = wait.until(ExpectedConditions.visibilityOf(signpage.welcomeText));

        Assert.assertTrue("Karşılama metni (" + expectedMessage + ") 8 saniye içinde görünür olmadı.", visibleWelcomeText.isDisplayed());
        System.out.println("Karşılama sayfası yüklendi ve başlık başarıyla doğrulandı.");
    }

    @When("The user chooses the {string} therapy area")
    public void the_user_chooses_the_therapy_area(String therapyArea) throws InterruptedException {
        signpage.clickMentalHealthCardByCoordinates();
        System.out.println("Terapi alanı: " + therapyArea + " seçildi.");
    }

    @When("The user taps on the {string} button to proceed to the form")
    public void the_user_taps_on_the_button_to_proceed_to_the_form(String buttonText) {
        ReusableMethods.clickElement(Driver.getAndroidDriver(), signpage.startButton, 5);

        System.out.println("Başlayın butonu (" + buttonText + ") güvenilir ID ile tıklandı ve sonraki sayfaya geçildi.");
    }

    @When("The user selects the {string} option")
    public void the_user_selects_the_option(String therapyType) throws InterruptedException {
        ReusableMethods.clickElement(Driver.getAndroidDriver(), signpage.individualTherapyButton, 5);
        System.out.println("Terapi türü: " + therapyType + " başarıyla seçildi.");
    }

    @And("The user clicks the {string} dropdown")
    public void theUserClicksTheDropdown(String dropdownText) throws InterruptedException {
        signpage.clicksAgeDropdownByCoordinates();
        System.out.println("Dropdown (" + dropdownText + ") ID ile başarıyla tıklandı.");
    }

    @And("The user selects the age {string} from the picker")
    public void theUserSelectsTheAgeFromThePicker(String age) {
        ReusableMethods.scrollWithUiScrollableAndClick(age);
        System.out.println("Yaş değeri: " + age + " başarıyla seçildi.");
    }

    @And("The user confirms the selection by tapping the {string} button")
    public void theUserConfirmsTheSelectionByTappingTheButton(String buttonText) throws InterruptedException {
        signpage.clicksOkButtonByCoordinates();
        System.out.println("Onay butonu (" + buttonText + ") koordinatla tıklandı.");
    }

    @And("The user selects the {string} {string} {string} problem")
    public void theUserSelectsTheProblem(String problemOne, String problemTwo, String problemThree ) {
        signpage.selectProblemsAndContinue(problemOne, problemTwo, problemThree);
        System.out.println("Sorunlar seçildi ve Devam butonu tıklandı. Akış Form'a yönlendiriliyor.");
    }

    @And("The user clicks the {string} button")
    public void theUserClicksTheButton(String continueText){
        signpage.continueButton.click();
    }

    @After
    public void tearDown() {
        Driver.quitAppiumDriver();
    }

}
