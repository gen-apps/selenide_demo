package org.demo.listeners;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.util.Optional;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        var screenshot = Optional.ofNullable(Selenide.screenshot(OutputType.BYTES))
                .orElseThrow();

        Allure.attachment(result.getTestName(), new ByteArrayInputStream(screenshot));
    }
}
