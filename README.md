# Job Platform Automation Tests

Automation test project for the local Job Platform application, kept separate from the application source repository.

## Stack
- Java 17+
- Maven
- Selenium WebDriver + JUnit 5
- REST Assured

## Verified local configuration
Based on the configuration supplied for the project:
- Candidate/recruiter frontend: `http://localhost:3000`
- Admin frontend: `http://localhost:3001`
- Backend: `http://localhost:4000`
- Health check: `GET /health`
- Login API: `POST /api/v1/login`
- UI login path: `/auth/login`

These are local defaults. Override them with Maven system properties if your local ports or routes change.

## Prerequisites
1. Start the Job Platform frontend, admin frontend, backend, and required services.
2. Install JDK 17 or newer and Maven.
3. Install Google Chrome.
4. Open this directory in VS Code. Recommended extension: Extension Pack for Java.

Selenium Manager normally resolves the Chrome driver automatically. If your network/environment blocks driver downloads, configure ChromeDriver separately.

## Run tests
Run all tests:
```bash
mvn clean test
```

Run only API tests:
```bash
mvn -Dtest='Api*Test,LoginApiValidationTest' test
```

Run only UI tests:
```bash
mvn -Dtest='*LoginPageSmokeTest' test
```

Override URLs:
```bash
mvn clean test \
  -Dui.base.url=http://localhost:3000 \
  -Dadmin.base.url=http://localhost:3001 \
  -Dapi.base.url=http://localhost:4000
```

## Current test coverage
- UI: candidate/recruiter login page controls; invalid-credential flow; admin login page controls.
- API: health check; login rejects invalid credentials and malformed email.

These are initial smoke/negative tests, not full business coverage. Do not claim a test passed until it has actually run in your environment.

## Test reports
After Maven runs, inspect:
- `target/surefire-reports/`

## Security
- Do not commit real passwords, access tokens, refresh tokens, `.env` files, or production data.
- This framework intentionally does not hard-code the supplied admin credentials.
- For future positive login tests, pass credentials via environment variables such as `TEST_ADMIN_EMAIL` and `TEST_ADMIN_PASSWORD`, and ensure the account is intended for automated testing.

## Suggested next tests
- Candidate registration validation
- Job search and job detail
- Candidate application submission
- Recruiter job creation and submission for approval
- Admin approve/reject job
- Authorization checks for `ADMIN`, `RECRUITER`, and `CANDIDATE`
- Refresh-token behavior and session revocation

## Important implementation note
The API test asserts common 4xx statuses for invalid input. Confirm the exact status contract in the backend's error handler/Zod validation middleware and adjust the accepted statuses if the application has a more specific contract.
