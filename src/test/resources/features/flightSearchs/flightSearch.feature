Feature: Comprehensive Flight Search

  Narrative:
  In order to plan my trips more effectively
  As a potential traveler
  I want to be able to search for available flights by specifying both departure and arrival locations, in addition to specific dates

  Scenario: Verify flights availability by origin, destination, and date
    Given I'm in the vueling page
    When I verify the existing flights:
      | source | destination | flightDate  | passengers |
      | Madrid | Barcelona   | 2024-06-01 | 1          |
    Then I should see flights available that match my specified origin, destination, and travel date
