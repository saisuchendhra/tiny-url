# tiny-url
This Git repository contains the implementation of a Tiny URL REST API, providing two endpoints:  
/encode: Encodes a long URL into a shortened URL. 
/decode: Decodes a shortened URL back into its original long URL. 
Both endpoints return JSON responses. 

## Prerequisites
- Java JDK 8 or higher installed
- Maven installed
- Git installed

## Setup Steps
1. Clone the repository:
    ```
    git clone https://github.com/saisuchendhra/tiny-url.git
    ```
2. Navigate to the project directory:
    ```
    cd tiny-url
    ```
3. Build the project:
    ```
    mvn clean install
    ```

## Running the Application
1. Start the application:
    ```
    java -jar target/com.solventum.tinyurl-0.0.1-SNAPSHOT.jar
    ```
2. Access the application in API Testing tool like Postman  at http://localhost:8080

3. Encode API Testing Instructions
### Description
Encodes a URL to a shortened URL.

### Request
- Method: POST
- URL: http://localhost:8080/api/v1/encode
- Headers: Content-Type: application/json
- Body:
    ```json
    {
        "url": "https://mail.google.com/mail/u/0/?tab=rm&ogbl#search/solven/FMfcgzGxSbtDWSPGdVJsWGnjCXTFNnGh"
    }
    ```

### Expected Response
- Status Code: 200 OK
- Body: [The shortened URL in JSON format](http://short.est/zd5yba9).

4. Decode API Testing Instructions

### Description
Decodes a shortened URL to its original URL.

### Request

- Method: GET
- URL: http://localhost:8080/api/v1/decode?tinyUrl=http://short.est/zd5yba9
- Headers: None
- Make sure to replace the "tinyUrl=http://short.est/zd5yba9" to the your tinyUrl generated in previous step in the Get URL.

### Expected Response
- Status Code: 200 OK
- Body: [The original URL in JSON format](https://mail.google.com/mail/u/0/?tab=rm&ogbl#search/solven/FMfcgzGxSbtDWSPGdVJsWGnjCXTFNnGh).