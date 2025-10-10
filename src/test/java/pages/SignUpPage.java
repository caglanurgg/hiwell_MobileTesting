package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;

public class SignUpPage {

    public SignUpPage(){
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()),this);
    }

    @FindBy(id = "com.hiwell:id/greetingsRegisterButton")
    public WebElement signUpButton;

    @FindBy(id = "com.hiwell:id/footerRedirectionLayoutText")
    public WebElement alreadyMember;

    @FindBy(id = "com.hiwell:id/greetingsLoginButton")
    public WebElement logInButton;

    @FindBy(id = "com.hiwell:id/changeLanguageTextView2")
    public WebElement selectYourLanguage;

    @FindBy(xpath = "//*[@text='Türkçe']")
    public WebElement turkceText;

    @FindBy(id = "com.hiwell:id/welcomeTextHeader")
    public WebElement welcomeText;

    @FindBy(id = "com.hiwell:id/startTestButton")
    public WebElement startButton;

    @FindBy(id = "com.hiwell:id/continueTestButton")
    public WebElement continueButton;

    @FindBy(xpath = "(//*[@resource-id='com.hiwell:id/choiceItemLayout'])[1]")
    public WebElement individualTherapyButton;


    public void selectTherapyLanguage(String languageName) {
        ReusableMethods.wait(5);

        WebElement languageElement = getLanguageButton(languageName);
        ReusableMethods.waitForVisibility(languageElement, 2);

        languageElement.click();
        System.out.println("Başarılı bir şekilde dil seçildi: " + languageName);
    }

    public WebElement getLanguageButton(String languageName) {
        String xpath = "//*[@text='" + languageName + "']";
        return Driver.getAndroidDriver().findElementByXPath(xpath);
    }

    public void clickMentalHealthCardByCoordinates() throws InterruptedException {
        int xkoordinati = 582;
        int ykoordinati = 1431;
        int beklemeSuresi = 600;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        ReusableMethods.koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void clickNutritionandDietByCoordinates() throws InterruptedException {
        int xkoordinati = 588;
        int ykoordinati = 2066;
        int beklemeSuresi = 500;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        ReusableMethods.koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void clickStartButtonByCoordinates() throws InterruptedException {
        int xkoordinati = 712;
        int ykoordinati = 2671;
        int beklemeSuresi = 700;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        ReusableMethods.koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
        ReusableMethods.wait(5);
        System.out.println("Başlayın butonu tıklandı ve sayfa yüklemesi için sabit süre beklendi.");
    }



    public void chooseIndividualTherapyByCoordinates() throws InterruptedException {
        int xkoordinati = 706;
        int ykoordinati = 1015;
        int beklemeSuresi = 500;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        ReusableMethods.koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void clicksAgeDropdownByCoordinates() throws InterruptedException {
        int xkoordinati = 635;
        int ykoordinati = 997;
        int beklemeSuresi = 500;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        ReusableMethods.koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void clicksOkButtonByCoordinates() throws InterruptedException {
        int xkoordinati = 1187;
        int ykoordinati = 1793;
        int beklemeSuresi = 500;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        ReusableMethods.koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void selectProblemsAndContinue(String problemOne, String problemTwo, String problemThree) {
        ReusableMethods.scrollWithUiScrollableAndClick(problemOne);
        ReusableMethods.scrollWithUiScrollableAndClick(problemTwo);
        ReusableMethods.scrollWithUiScrollableAndClick(problemThree);
    }


}



