# GitHubApplication

Description:
The project is an application that consumes the GitHub API to retrieve a list of user repositories and information about branches for each repository:

-Repository Name

-Owner Login

-For each branch it’s name and last commit sha

Requirements: 
Java 17 or higher
Maven 3.6.3 or higher
Spring Boot 2.5.5 or higher
RESTful API client (e.g., Postman) for testing endpoints
GitHub Access Token

Installation and Running:
Clone the repository to your local machine

Endpoints: 
GET /repositories/{username} - Retrieves a list of GitHub user repositories.
GET /health - Checks the health status of the application.
Example Usage
To retrieve a list of repositories for a user with the username "exampleuser", send a GET request to the /repositories/exampleuser endpoint.

Generating Access Token: 
To use the GitHub API, you need to generate a personal access token with the appropriate scopes and permissions. Follow these steps to generate a token:

Go to your GitHub account settings.
1. Navigate to "Developer settings" > "Personal access tokens".
2. Click on "Generate new token".
3. Select the scopes and permissions required for your application (e.g., repo for accessing repositories).
4. Click on "Generate token" and copy the generated token.
5. Use this token in your application for authenticating requests to the GitHub API.
6. Make sure to keep your access token secure and do not expose it publicly.

Response Format: 
List of Repositories Response

"repositories": [
{
"name": "repo1",
"ownerLogin": "exampleuser",
"branches": [
{
"name": "main",
"sha": "5a6820caa4abc74e437d99ccf5007893e74414cc"
},
{
"name": "feature-branch",
"sha": "9b9a8180e8e4e97fc11ff8912928a905f9ed12c3"
}
]
},
{
"name": "repo2",
"ownerLogin": "exampleuser",
"branches": [
{
"name": "main",
"sha": "d8a183bee41049d7116edcc19fae0f605d62cbe3"
}
]
}
]
}

User Not Found Response: 
{
"status": 404,
"message": "User not found"
}

Author: 
Aleksandra Cielecka - aleksandratobuch27.03@gmail.com