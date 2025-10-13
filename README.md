#  Hiwell Mobile Testing Project

This project focuses on automating the **sign-up flow** in the Hiwell mobile application, specifically within the **therapy service** area.  
The goal is to validate that a new user can successfully navigate the onboarding process — including language selection, therapy area choice, age, gender, and problem details — using Appium and Cucumber.

---

##  Project Overview

Hiwell is a mental health platform that offers therapy and dietitian services.  
In this project, I focused only on the **therapy flow**. The test case simulates a new user who:

1. Launches the Hiwell app
2. Selects the preferred language (e.g., Turkish, French)
3. Chooses the therapy area
4. Fills in personal information such as age, gender, and problems
5. Proceeds to the next onboarding step

The flow ends right before therapist selection, as that part requires real backend interaction and live matching.

---

##  Tech Stack

- **Java 11 (Temurin JDK)**
- **Maven** as the build system
- **Appium** for mobile automation
- **JUnit & Cucumber (BDD)** for test structure and execution
- **Android Studio (API Level 31 / Android 12)** as emulator environment

---

##  Project Structure
```
hiwell_MobileTesting
│
├── src/test/java
│   ├── pages/          # Page Object files
│   ├── stepDefinitions # Step definitions for each feature
│   ├── runner/     # Test runner configurations
│   ├── utilities/      # Driver, config reader, and helper classes
│
├── src/test/resources
│   ├── features/       # Feature files (BDD scenarios)
│   └── configuration.properties
│
├── pom.xml
└── README.md
```

---

##  Security Notes

- Sensitive data such as `appPackage` and `appActivity` are **hidden** in the public repository.
- The `Apps/` folder containing the APK file is also **excluded** from GitHub.
- Configuration files are structured to protect private data and credentials.

---

##  Reporting

Cucumber automatically generates a **JSON report** after each test run: 
```sh
target/cucumber.json
```

This file contains detailed information about test execution and can later be converted into an HTML report if needed.

---

##  Key Notes

- Turkish language scenario runs **successfully without issues**.
- The French scenario currently stops at the **Age dropdown** step due to element interaction delay.
- The test flow demonstrates realistic mobile testing challenges and solutions, including synchronization, locator strategies, and multi-language handling.

---

##  Development Timeline

- **Day 1–2:** Environment setup (Appium, Android Emulator, project configuration)
- **Day 3:** Test case design and Turkish scenario completion
- **Day 4:** French scenario development and debugging up to dropdown step

---

##  Final Note

This project was built from scratch in **four days**, including APK extraction and setup.  
Every step — from converting the XAPK to crafting the automation flow — reflects problem-solving, adaptability, and persistence.  
Further steps (therapist matching and session flow) will be added in the future iterations.

---
##  About

All test cases, structure, and framework logic were written by **Çağlanur Göğüş** as part of her mobile testing practice project for Hiwell.
The aim was to simulate a real QA automation workflow — from manual exploration to automation and reporting.

