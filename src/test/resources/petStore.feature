Feature: I want add pet into pet store

  Scenario: Add pet into store
    Given Go to /
    When Add pet
    Then Return status 200
    And Return Pet with name doggie

  Scenario: Get all pets with status sold
    Given Go to /findByStatus
    When I send request with parameter status equals sold
    Then Return status 200