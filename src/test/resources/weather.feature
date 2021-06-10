Feature: I want to test openweathermap.org api

  Scenario: I want to find city by name
    Given I create url for search Moscow
    When I send request
    Then I get response with status ok
    And I get response with parameter message equal accurate

  Scenario: I want to get weather in Moscow
    Given I create url for get weather in Moscow
    When I send request
    Then I get response with status ok
    And I get response with parameter name equal Moscow