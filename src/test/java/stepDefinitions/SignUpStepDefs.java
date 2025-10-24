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
        signpage.selectTherapyArea(therapyArea);
        System.out.println("Terapi alanı: " + therapyArea + " seçildi.");
    }

    @When("The user taps on the {string} button to proceed to the form")
    public void the_user_taps_on_the_button_to_proceed_to_the_form(String buttonText) {
        ReusableMethods.wait(5);
        ReusableMethods.clickElement(Driver.getAndroidDriver(), signpage.startButton, 7);
        System.out.println("Başlayın butonu (" + buttonText + ") güvenilir ID ile tıklandı ve sonraki sayfaya geçildi.");
    }

    @And("The user selects the {string} option")
    public void the_user_selects_the_option(String therapyType) throws InterruptedException {
        ReusableMethods.scrollWithUiScrollableAndClick(therapyType);
        signpage.setSelectedTherapyType(therapyType);

        ReusableMethods.wait(3);
        System.out.println("Terapi türü: " + therapyType + " başarıyla seçildi ve yeni sayfaya geçildi.");
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
        ReusableMethods.wait(2);
    }

    @When("The user proceeds through the form and completes the sign-up")
    public void the_user_proceeds_through_the_form_and_completes_the_sign_up()  throws InterruptedException {
        String continueButtonText = signpage.getSelectedLanguage().equals("Français") ? "Commencer" : "Devam Edin";
        String okButtonText = signpage.getAgePickerOkButtonText();

        signpage.clicksAgeDropdownDynamically(signpage.getAgeDropdownText());
        ReusableMethods.wait(2);
        signpage.scrollAndSelectAgeDynamically("26");
        ReusableMethods.wait(1);
        signpage.clicksOkButtonDynamically(okButtonText);
        ReusableMethods.wait(2);

        String gender = signpage.getSelectedGender();
        ReusableMethods.scrollWithUiScrollableAndClick(gender);

        String[] problems = signpage.getSelectedProblems();
        signpage.selectProblemsAndContinue(problems[0], problems[1], problems[2]);
        theUserClicksTheButton(continueButtonText);
        ReusableMethods.wait(2);

        String[] preferences = signpage.getSelectedTherapistPreferences();
        signpage.selectTherapistPreferencesAndContinue(preferences);
        theUserClicksTheButton(continueButtonText);
        ReusableMethods.wait(2);

        String therapistGender = signpage.getSelectedTherapistGender();
        signpage.selectTherapistGenderDynamically(therapistGender);
        ReusableMethods.wait(2);

        String therapyPast = signpage.getPreviousTherapyStatus();
        signpage.selectTherapyPastStatus(therapyPast);
        ReusableMethods.wait(2);

        String[] timeSlots = signpage.getSelectedTimeSlots();
        signpage.selectTimeSlotsAndContinue(timeSlots);
        theUserClicksTheButton(continueButtonText);
        ReusableMethods.wait(2);

        String financialStatus = signpage.getSelectedFinancialStatus();
        signpage.selectFinancialStatusDynamically(financialStatus);
        ReusableMethods.wait(2);
    }

    @Then("The user should see the {string} confirmation")
    public void the_user_should_see_the_confirmation(String expectedText) {
        WebElement confirmation = signpage.getConfirmationElement();
        Assert.assertTrue(confirmation.isDisplayed());
        System.out.println("Therapist Match confirmation görüntülendi: " + expectedText);
    }
}
