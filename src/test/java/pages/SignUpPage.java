package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;

import static utilities.ReusableMethods.koordinatTiklamaMethodu;

public class SignUpPage {

    public SignUpPage(){
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()),this);
    }

    private String selectedLanguage;

    public void setSelectedLanguage(String language) {
        this.selectedLanguage = language;
    }

    public String getSelectedLanguage() {
        return this.selectedLanguage;
    }

    public String getAgeDropdownText() {
        return selectedLanguage.equals("Français") ? "Votre âge" : "Yaşınız";
    }

    public String getSelectedGender() {
        if(selectedLanguage.equals("Français")) return "Femme";
        else return "Kadın";
    }

    public String[] getSelectedProblems() {
        if(selectedLanguage.equals("Français"))
            return new String[]{"Anxiété et inquiétude", "Mauvaises habitudes", "Stress"};
        else
            return new String[]{"Kaygı ve endişe", "Kötü alışkanlıklar", "Stres"};
    }

    public String[] getSelectedTherapistPreferences() {
        if (selectedLanguage.equals("Français"))
            return new String[]{"M’écouter avant tout", "Faire des activités et les devoirs"};
        else
            return new String[]{"Düşünce ve görüşlerimi sorgulatan", "Davranış örüntülerimi keşfettiren"};
    }

    public WebElement getConfirmationElement() {
        return Driver.getAndroidDriver().findElement(By.id("com.hiwell:id/therapistMatchText"));
    }

    private String selectedTherapyType;

    public void setSelectedTherapyType(String therapyType) {
        this.selectedTherapyType = therapyType;
    }

    public String getSelectedTherapyType() {
        if (selectedTherapyType == null) {
            throw new RuntimeException("Therapy type henüz set edilmedi!");
        }
        return selectedTherapyType;
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

    @FindBy(id = "com.hiwell:id/startTestButton")
    public WebElement startButton;

    @FindBy(id = "com.hiwell:id/continueTestButton")
    public WebElement continueButton;

    @FindBy(xpath = "(//*[@resource-id='com.hiwell:id/choiceItemLayout'])[1]")
    public WebElement individualTherapyButton;

    @FindBy(id = "com.hiwell:id/hwEditTextView")
    public WebElement yourAgeDropdown;

    public void selectTherapyLanguage(String languageName) {
        ReusableMethods.wait(5);

        WebElement languageElement = getLanguageButton(languageName);
        ReusableMethods.waitForVisibility(languageElement, 2);

        languageElement.click();
        System.out.println("Başarılı bir şekilde dil seçildi: " + languageName);
    }

    public WebElement getWelcomeTextElement(String expectedMessage) {
        try {
            return Driver.getAndroidDriver().findElementById("com.hiwell:id/welcomeTextHeader");
        } catch (Exception e) {
            try {
                return Driver.getAndroidDriver().findElementById("com.hiwell:id/sliderHeadingInitial");
            } catch (Exception ex) {
                throw new RuntimeException("Welcome/Karşılama metni bulunamadı. ID'ler değişmiş olabilir.");
            }
        }
    }

    public WebElement getLanguageButton(String languageName) {
        String xpath = "//*[@text='" + languageName + "']";
        return Driver.getAndroidDriver().findElementByXPath(xpath);
    }

    public void selectTherapyArea(String therapyArea) throws InterruptedException {
        AndroidDriver driver = Driver.getAndroidDriver();

        String uiAutomatorCommand = "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"" + therapyArea + "\").instance(0))";

        try {
            WebElement therapyCard = driver.findElementByAndroidUIAutomator(uiAutomatorCommand);
            ReusableMethods.wait(1);

            // Element bulunduktan sonra merkez koordinatlarını hesaplayıp tıklıyoruz (Stabilite)
            int elementCenterX = therapyCard.getLocation().getX() + (therapyCard.getSize().getWidth() / 2);
            int elementCenterY = therapyCard.getLocation().getY() + (therapyCard.getSize().getHeight() / 2);

            koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);

            System.out.println("Terapi alanı: " + therapyArea + " başarıyla bulundu ve tıklandı.");

        } catch (Exception e) {
            System.err.println("HATA: Terapi Alanı Seçimi Başarısız: " + therapyArea);
            throw new RuntimeException("Terapi alanı seçilemedi: " + therapyArea, e);
        }
    }

    public void clickNutritionandDietByCoordinates() throws InterruptedException {
        int xkoordinati = 588;
        int ykoordinati = 2066;
        int beklemeSuresi = 500;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void clickStartButtonByCoordinates() throws InterruptedException {
        int xkoordinati = 712;
        int ykoordinati = 2671;
        int beklemeSuresi = 700;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
        ReusableMethods.wait(5);
        System.out.println("Başlayın butonu tıklandı ve sayfa yüklemesi için sabit süre beklendi.");
    }

    public void chooseIndividualTherapyByCoordinates() throws InterruptedException {
        int xkoordinati = 706;
        int ykoordinati = 1015;
        int beklemeSuresi = 700;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void clicksAgeDropdownByCoordinates() throws InterruptedException {
        int xkoordinati = 695;
        int ykoordinati = 985;
        int beklemeSuresi = 500;

        ReusableMethods.wait(2);

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        ReusableMethods.koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void clicksAgeDropdownDynamically(String dropdownText) throws InterruptedException {
        AndroidDriver driver = Driver.getAndroidDriver();

        String xpath = "//*[contains(@text, '" + dropdownText + "')]";

        try {
            WebElement ageDropdownElement = driver.findElementByXPath(xpath);

            int elementCenterX = ageDropdownElement.getLocation().getX() + (ageDropdownElement.getSize().getWidth() / 2);
            int elementCenterY = ageDropdownElement.getLocation().getY() + (ageDropdownElement.getSize().getHeight() / 2);

            ReusableMethods.koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);
            System.out.println("Dropdown dinç koordinatla tıklandı: X=" + elementCenterX + ", Y=" + elementCenterY);

        } catch (Exception e) {
            System.err.println("HATA: Dropdown Dinamik Tıklaması Başarısız: " + dropdownText);
            throw new RuntimeException("Yaş dropdown elementi bulunamadı. Metin: " + dropdownText, e);
        }
    }

    public void scrollAndSelectAge(String targetAge) {
        AndroidDriver driver = Driver.getAndroidDriver();

        String uiScrollable = "new UiScrollable(new UiSelector().scrollable(true))";
        String uiAutomatorCommand = uiScrollable + ".scrollIntoView(new UiSelector().text(\"" + targetAge + "\").instance(0))";

        try {
            WebElement ageElement = driver.findElementByAndroidUIAutomator(uiAutomatorCommand);
            ReusableMethods.wait(1);
            ageElement.click();
        } catch (Exception e) {
            System.err.println("Yaş seçicide " + targetAge + " bulunamadı veya tıklanamadı: " + e.getMessage());
            try {
                clickPickerCenterByCoordinates();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void clickPickerCenterByCoordinates() throws InterruptedException {
        int xkoordinati = 712;
        int ykoordinati = 1383;
        int beklemeSuresi = 500;

        System.out.println("Koordinat ile tıklanıyor (Picker Ortası): X=" + xkoordinati + ", Y=" + ykoordinati);
        koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public void clicksOkButtonByCoordinates() throws InterruptedException {
        int xkoordinati = 1187;
        int ykoordinati = 1793;
        int beklemeSuresi = 500;

        System.out.println("Koordinat ile tıklanıyor: X=" + xkoordinati + ", Y=" + ykoordinati);

        koordinatTiklamaMethodu(xkoordinati, ykoordinati, beklemeSuresi);
    }

    public static void selectProblemSafely(String problemText) {
        AndroidDriver driver = (AndroidDriver) Driver.getAndroidDriver();

        String uiAutomatorCommand = "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"" + problemText + "\").instance(0))";

        try {
            WebElement problemElement = driver.findElementByAndroidUIAutomator(uiAutomatorCommand);
            ReusableMethods.wait(1);

            int elementCenterX = problemElement.getLocation().getX() + (problemElement.getSize().getWidth() / 2);
            int elementCenterY = problemElement.getLocation().getY() + (problemElement.getSize().getHeight() / 2);

            koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);

            System.out.println("Başarıyla seçildi: " + problemText);

        } catch (Exception e) {
            System.err.println("HATA: Güvenli Problem Seçimi Başarısız: " + problemText);
            throw new RuntimeException("Problem seçimi başarısız: " + problemText, e);
        }
    }

    public void selectProblemsAndContinue(String problemOne, String problemTwo, String problemThree) {
        selectProblemSafely(problemOne);
        ReusableMethods.wait(1);

        selectProblemSafely(problemTwo);
        ReusableMethods.wait(1);

        selectProblemSafely(problemThree);
        ReusableMethods.wait(1);
    }

    public void selectTherapistPreferenceDynamically(String preferenceText) {
        AndroidDriver driver = Driver.getAndroidDriver();

        String xpath = String.format("//android.widget.TextView[@resource-id='com.hiwell:id/choiceItemText' and @text='%s']", preferenceText);

        try {
            String uiScrollableCommand = "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().text(\"" + preferenceText + "\").instance(0))";

            driver.findElementByAndroidUIAutomator(uiScrollableCommand);

            WebElement preferenceElement = driver.findElementByXPath(xpath);
            ReusableMethods.wait(1);

            int elementCenterX = preferenceElement.getLocation().getX() + (preferenceElement.getSize().getWidth() / 2);
            int elementCenterY = preferenceElement.getLocation().getY() + (preferenceElement.getSize().getHeight() / 2);

            ReusableMethods.koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);

            System.out.println("Başarıyla seçildi (Dinamik XPath): " + preferenceText);

        } catch (Exception e) {
            System.err.println("HATA: Dinamik Terapist Tercihi Seçimi Başarısız: " + preferenceText + " | XPath: " + xpath);
            throw new RuntimeException("Terapist tercihi seçimi başarısız: " + preferenceText, e);
        }
    }

    public void selectTherapistPreferencesAndContinue(String... preferenceTexts) {
        for (String preferenceText : preferenceTexts) {
            selectTherapistPreferenceDynamically(preferenceText);
            ReusableMethods.wait(1);
        }
    }
}



