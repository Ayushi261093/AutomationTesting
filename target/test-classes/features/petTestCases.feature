#Author: tulsyan.ayushi5@gmail.com

@tag
Feature: PET Shop API Test Cases

  @tag1
  Scenario Outline: Test PET STORE flow
    Given I hit on GET API to get list of "<type>" pets
    And I validate output for having list of available pets
    Then I hit POST API to post a new pet to store with "<name>" 
    And I hit API with pet Id to validate pet is posted successfully to store
    Then I update status of above pet id to new "<newStatus>"
    And I validate status to be updated using find pet by status api
    Then I delete this pet by id
    
    Examples: 
      | type      | name      | newStatus  |
      | available | newDoggie | sold       |
      