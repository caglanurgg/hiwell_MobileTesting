package stepDefinitions;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignUpPage;
import utilities.Driver;
import utilities.ReusableMethods;

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
        WebElement welcomeElement = signpage.getWelcomeTextElement(expectedMessage);

        WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), 3);
        WebElement visibleWelcomeText = wait.until(ExpectedConditions.visibilityOf(welcomeElement));

        Assert.assertTrue("Karşılama metni 3 saniye içinde görünür olmadı.", visibleWelcomeText.isDisplayed());
        System.out.println("Karşılama sayfası yüklendi ve başlık başarıyla doğrulandı.");
    }

    @When("The user chooses the {string} therapy area")
    public void the_user_chooses_the_therapy_area(String therapyArea) throws InterruptedException {
        signpage.clickMentalHealthCardByCoordinates();
        System.out.println("Terapi alanı: " + therapyArea + " seçildi.");
    }

    @When("The user taps on the {string} button to proceed to the form")
    public void the_user_taps_on_the_button_to_proceed_to_the_form(String buttonText) {
        ReusableMethods.wait(3);
        ReusableMethods.clickElement(Driver.getAndroidDriver(), signpage.startButton, 5);
        System.out.println("Başlayın butonu (" + buttonText + ") güvenilir ID ile tıklandı ve sonraki sayfaya geçildi.");
    }

    @When("The user selects the {string} option")
    public void the_user_selects_the_option(String therapyType) throws InterruptedException {

        if (therapyType.equals("Thérapie Individuelle")) {
            int xKoord = 689;
            int yKoord = 997;

            ReusableMethods.koordinatTiklamaMethodu(xKoord, yKoord, 500);
            System.out.println("Koordinat ile tıklandı: X=" + xKoord + ", Y=" + yKoord);

        } else {
            ReusableMethods.scrollWithUiScrollableAndClick(therapyType);
        }

        ReusableMethods.wait(3);
        System.out.println("Terapi türü: " + therapyType + " başarıyla seçildi ve yeni sayfaya geçildi.");
    }

    /*
    @And("The user clicks the {string} dropdown")
    public void theUserClicksTheDropdown(String dropdownText) throws InterruptedException {
        signpage.clicksAgeDropdownDynamically(dropdownText);
        System.out.println("Dropdown (" + dropdownText + ") başarıyla tıklandı.");
    }
     */

    @And("The user clicks the {string} dropdown")
    public void theUserClicksTheDropdown(String dropdownText) throws InterruptedException {
        signpage.clicksAgeDropdownByCoordinates();
        ReusableMethods.wait(2);
        System.out.println("Dropdown (" + dropdownText + ") statik koordinatla tıklandı.");
    }

    @And("The user selects the age {string} from the picker")
    public void theUserSelectsTheAgeFromThePicker(String age) {
        signpage.scrollAndSelectAge(age);
        ReusableMethods.wait(2);
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

    @And("The user closes the application")
    public void theUserClosesTheApplication() {
        Driver.quitAppiumDriver();
    }

    @And("The user selects the {string} language")
    public void theUserSelectsTheLanguage(String language) {
        ReusableMethods.wait(2);
        ReusableMethods.scrollWithUiScrollableAndClick(language);

        signpage.setSelectedLanguage(language);

        System.out.println("Dil başarıyla seçildi: " + language);
    }

    @When("The user proceeds through the form and completes the sign-up")
    public void the_user_proceeds_through_the_form_and_completes_the_sign_up()  throws InterruptedException {

        String therapyType = signpage.getSelectedTherapyType();
        the_user_selects_the_option(therapyType);

        theUserClicksTheDropdown(signpage.getAgeDropdownText());
        theUserSelectsTheAgeFromThePicker("27");
        theUserConfirmsTheSelectionByTappingTheButton("TAMAM");

        String gender = signpage.getSelectedGender();
        ReusableMethods.scrollWithUiScrollableAndClick(gender);

        String[] problems = signpage.getSelectedProblems();
        theUserSelectsTheProblem(problems[0], problems[1], problems[2]);

        theUserClicksTheButton("Devam Edin");
    }

    @Then("The user should see the {string} confirmation")
    public void the_user_should_see_the_confirmation(String expectedText) {
        WebElement confirmation = signpage.getConfirmationElement();
        Assert.assertTrue(confirmation.isDisplayed());
        System.out.println("Therapist Match confirmation görüntülendi: " + expectedText);
    }
}
