package org.openweathermap.api.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class MailPage {
    public MailPage goTo(String url) {
        open(url);
        return this;
    }

    public MailPage insertLogin(String login) {
        $x("//input[@data-testid='login-input']")
                .should(Condition.visible)
                .sendKeys(login);
        return this;
    }

    public MailPage insertPassword(String password) {
        $x("//button[@data-testid='enter-password']")
                .should(Condition.visible)
                .click();
        $x("//input[@data-testid='password-input']")
                .should(Condition.visible)
                .setValue(password);
        return this;
    }

    public MailPage clickSubmitButton() {
        $x("//button[@data-testid='login-to-mail']")
                .should(Condition.visible)
                .click();
        return this;
    }

    public boolean isEmailLoginSuccessfully() {
        return $x("//div[contains(text(), 'Входящие')]")
                .should(Condition.visible, Duration.ofMillis(10000))
                .exists();
    }

    public int countMessagesIntoMailBox(int count) {
        return $$x("//a[contains(@class, 'llc js-tooltip-direction_letter-bottom')]")
                .should(CollectionCondition.size(count))
                .size();
    }
}
