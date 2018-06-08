Feature: Google Test

  To demo cucumber

  @test
  Scenario: Go to google and search
    Given I navigate to google.com
    And I search for something
    Then Search should show