Feature: Create a new Incident using Rest Assured

Scenario: Create Incident(with no body)
Given end point is loaded
And basic auth is set
And set Content-Type as JSON
When i do a POST request
Then the response code should be 201

Scenario Outline: Create Incident(with body)
Given end point is loaded
And basic auth is set
And set Content-Type as JSON
And send body from  file <files>
When i do a POST request
Then the response code should be 201
Examples:
|files|
|createIncident.json|
|createIncident2.json|