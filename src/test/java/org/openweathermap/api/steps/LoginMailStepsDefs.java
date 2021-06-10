package org.openweathermap.api.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openweathermap.api.page.MailPage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginMailStepsDefs {
    private MailPage mailPage = new MailPage();

    @Given("^I go to (.*)$")
    public void goToUrl(String url) {
        mailPage.goTo(url);
    }

    @When("^I insert login (.*)$")
    public void insertLogin(String login) {
        mailPage.insertLogin(login);
    }

    @And("^I insert password (.*)$")
    public void insertPassword(String password) {
        mailPage.insertPassword(password)
                .clickSubmitButton();
    }

    @Then("^I can see incoming messages button$")
    public void buttonIsVisible() {
        assertTrue(mailPage.isEmailLoginSuccessfully());
    }

    @And("^I can see (.*) incoming messages into box$")
    public void checkCountMessages(int count) {
        assertThat(mailPage.countMessagesIntoMailBox(count), is(count));
    }
}
