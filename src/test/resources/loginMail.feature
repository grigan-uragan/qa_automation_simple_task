Feature: As a user
  I want to login in mailbox account

  Scenario: Login to mail.ru
    Given I go to https://mail.ru
    When I insert login testprischepny
    And I insert password grigan85
    Then I can see incoming messages button
    And I can see 6 incoming messages into box