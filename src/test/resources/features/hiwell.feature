@apk
Feature: Appium Baglanti Testi
  Amac: Appium driver'in baslatilip, uygulamanin cihaza kurulmasini kontrol et

  Scenario: Uygulama Baslatma Testi
    Given Kullanici ilgili apkyi "C:\Users\Cagla\IdeaProjects\hiwell_MobileTesting\Apps\apk_bilgisi_2.3.4_apkcombo.com.apk" cep telefonuna yukler
    Then Kullanici uygulamanin basarili bir sekilde acildigini dogrular