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

            int elementCenterX = therapyCard.getLocation().getX() + (therapyCard.getSize().getWidth() / 2);
            int elementCenterY = therapyCard.getLocation().getY() + (therapyCard.getSize().getHeight() / 2);

            koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);

            System.out.println("Terapi alanı: " + therapyArea + " başarıyla bulundu ve tıklandı.");

        } catch (Exception e) {
            System.err.println("HATA: Terapi Alanı Seçimi Başarısız: " + therapyArea);
            throw new RuntimeException("Terapi alanı seçilemedi: " + therapyArea, e);
        }
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

    public void scrollAndSelectAgeDynamically(String targetAge) {
        AndroidDriver driver = Driver.getAndroidDriver();

        String uiScrollable = "new UiScrollable(new UiSelector().scrollable(true))";
        String uiAutomatorCommand = uiScrollable + ".scrollIntoView(new UiSelector().text(\"" + targetAge + "\").instance(0))";

        try {
            WebElement ageElement = driver.findElementByAndroidUIAutomator(uiAutomatorCommand);
            ReusableMethods.wait(1);

            int elementCenterX = ageElement.getLocation().getX() + (ageElement.getSize().getWidth() / 2);
            int elementCenterY = ageElement.getLocation().getY() + (ageElement.getSize().getHeight() / 2);

            koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);

            System.out.println("Yaş değeri: " + targetAge + " başarıyla seçildi.");
        } catch (Exception e) {
            System.err.println("Yaş seçicide " + targetAge + " bulunamadı veya tıklanamadı: " + e.getMessage());
            throw new RuntimeException("Yaş seçilemedi: " + targetAge, e);
        }
    }

    public void clicksOkButtonDynamically(String buttonText) throws InterruptedException {
        AndroidDriver driver = Driver.getAndroidDriver();
        String xpath = "//*[@text='" + buttonText + "']";

        try {
            WebElement okButton = driver.findElementByXPath(xpath);

            int elementCenterX = okButton.getLocation().getX() + (okButton.getSize().getWidth() / 2);
            int elementCenterY = okButton.getLocation().getY() + (okButton.getSize().getHeight() / 2);

            koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);

            System.out.println("Onay butonu (" + buttonText + ") dinamik olarak tıklandı.");
        } catch (Exception e) {
            throw new RuntimeException("Onay butonu bulunamadı: " + buttonText, e);
        }
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

    public String getAgePickerOkButtonText() {
        return selectedLanguage.equals("Français") ? "OK" : "TAMAM";
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

    public String getPreviousTherapyStatus() {
        if (selectedLanguage.equals("Français")) {
            return "Oui";
        } else {
            return "Evet";
        }
    }

    public void selectTherapyPastStatus(String statusText) {
        Driver.getAndroidDriver().findElementByXPath("//*[@text='" + statusText + "']").click();
        System.out.println("Terapist geçmişi seçildi: " + statusText);
    }

    public void clickContinueButtonDynamically(String buttonText) throws InterruptedException {
        AndroidDriver driver = Driver.getAndroidDriver();

        String xpath = String.format("//*[@text='%s']", buttonText);

        try {
            WebElement continueButton = driver.findElementByXPath(xpath);

            int elementCenterX = continueButton.getLocation().getX() + (continueButton.getSize().getWidth() / 2);
            int elementCenterY = continueButton.getLocation().getY() + (continueButton.getSize().getHeight() / 2);

            koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);

            System.out.println("Devam Edin butonu (" + buttonText + ") dinamik olarak tıklandı.");
        } catch (Exception e) {
            System.err.println("HATA: Devam Edin Butonu bulunamadı: " + buttonText);

            throw new RuntimeException("Devam Edin butonu tıklanamadı: " + buttonText, e);
        }
    }

    public String getSelectedTherapistGender() {
        if (selectedLanguage.equals("Français")) {
            return "Femme";
        } else {
            return "Kadın";
        }
    }

    public void selectTherapistGenderDynamically(String genderText) {
        AndroidDriver driver = Driver.getAndroidDriver();

        String xpath = String.format("//*[@text='%s']", genderText);

        try {
            WebElement genderElement = driver.findElementByXPath(xpath);

            int elementCenterX = genderElement.getLocation().getX() + (genderElement.getSize().getWidth() / 2);
            int elementCenterY = genderElement.getLocation().getY() + (genderElement.getSize().getHeight() / 2);

            koordinatTiklamaMethodu(elementCenterX, elementCenterY, 500);

            System.out.println("Terapist Cinsiyeti başarıyla seçildi: " + genderText);

        } catch (Exception e) {
            System.err.println("HATA: Terapist Cinsiyeti bulunamadı: " + genderText);
            throw new RuntimeException("Terapist Cinsiyeti seçimi başarısız: " + genderText, e);
        }
    }

    public String[] getSelectedTimeSlots() {
        if (selectedLanguage.equals("Français")) {
            return new String[]{"En semaine - Matin", "En semaine - Après-midi"};
        } else {
            return new String[]{"Hafta İçi - Sabah (09:00 - 12:00)", "Hafta İçi - Öğle (12:00 - 16:00)"};
        }
    }

    public void selectTimeSlotsAndContinue(String... timeSlots) throws InterruptedException {
        AndroidDriver driver = Driver.getAndroidDriver();

        for (String slot : timeSlots) {
            String xpath = String.format("//*[@text='%s']", slot);
            try {
                WebElement slotElement = driver.findElementByXPath(xpath);
                slotElement.click();
                ReusableMethods.wait(3);
            } catch (Exception e) {
                throw new RuntimeException("Saat slotu bulunamadı: " + slot, e);
            }
        }
        System.out.println(timeSlots.length + " adet saat slotu seçildi.");
    }

    public String getSelectedFinancialStatus() {
        if (selectedLanguage.equals("Français")) {
            return "Très bien";
        } else {
            return "İyi";
        }
    }

    public void selectFinancialStatusDynamically(String statusText) {
        AndroidDriver driver = Driver.getAndroidDriver();
        String xpath = String.format("//*[@text='%s']", statusText);

        try {
            driver.findElementByXPath(xpath).click();
            System.out.println("Ekonomik Durum başarıyla seçildi: " + statusText);
        } catch (Exception e) {
            throw new RuntimeException("Ekonomik Durum seçimi başarısız: " + statusText, e);
        }
    }
}



