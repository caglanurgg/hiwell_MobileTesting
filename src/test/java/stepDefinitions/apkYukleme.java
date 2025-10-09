package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import utilities.Driver;

public class apkYukleme {
    @Given("Kullanici ilgili apkyi {string} cep telefonuna yukler")
    public void kullanici_ilgili_apkyi_cep_telefonuna_yukler(String apkYolu) {
        // Step'in amacı Appium Driver'ı başlatmaktır.
        // Tüm ayarlar (Capabilities) utilities.Driver içinde yönetilir.
        Driver.getAndroidDriver();
    }

    @Then("Kullanici uygulamanin basarili bir sekilde acildigini dogrular")
    public void kullanici_uygulamanin_basarili_bir_sekilde_acildigini_dogrular() {
        // Driver nesnesinin null olmadığını kontrol ederiz.
        // Eğer driver başarılı bir şekilde oluşturulduysa, uygulama açılmış demektir.
        Assert.assertNotNull("Appium Driver baslatilamadi, Baglanti Hatasi!", Driver.getAndroidDriver());

        // Ekstra kontrol: Uygulamanın paket adını kontrol edebiliriz
        String currentPackage = Driver.getAndroidDriver().getCurrentPackage();
        Assert.assertEquals("Acilan uygulama paketi Hiwell degil!", "com.hiwell", currentPackage);
    }
}
