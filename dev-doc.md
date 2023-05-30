
<a name="readme-top"></a>
# DEVELOPER DOCUMENTATION

## Obtaining Source Code
All the source code for our Workoutholic App is contained within the "m-arquiza/Workoutholic" repository. To access the source code, clone this repository.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Directory Layout

| Project Directories     | Brief Description          |
|-------------------------|----------------------------|
| [`/github/workflows`](./github/workflows) | Files related to Gradle and the CI/CD pipeline. |
| [`/.idea`](./.idea) | Files related to Gradle and the CI/CD pipeline. |
| [`/gradle/wrapper`](./gradle/wrapper) | Files related to Gradle and the CI/CD pipeline. |
| [`/reports`](./reports) | Contains weekly reports from the team on our progress. |
| [`/app`](./app)         | Stores our app files.      |
| [`/app/src`](./app/src)         | Contains test and source code files.      |
| [`/app/src/main/java/com/example/workoutholicapp`](./app/src/main/java/com/example/workoutholicapp)         | Backend and frontend code for the app.     |

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Building
1. Prerequisites
    1. JDK version 1.8 or newer is installed.
    2. Development environment that supports Android app development (Android Studio preferred) is installed.
    3. Repository is cloned and launched in Android Studio.
2. Building
    1. On the left sidebar, click the project tab.
    2. Expand the "Gradle Scripts" folder, then open the "build.gradle" file for the app (e.g., "build.gradle (Module: app)").
    3. If a notification pops up at the top asking to sync Gradle files, click "Sync now".
    4. Underneath the navigation ribbon, next to the forward and back buttons, click the green hammer symbol ("Make Project").
        - If it does not exist or is not highlighted, ensure that the "Run/Debug Configuration" is set to "app".

### How to Build a Release
Ensure that Java Version 17 is installed on your computer before running the application. Update the version number if necessary or if issues are encountered while building the release.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Testing
### How to test the software
If testing in an Android app development environment, run the tests that are named after the class you want to test (e.g., if you want to test the shop functionalities, run the tests under BasicShopTests.java). If testing the entire software, run the tests on every class.
If testing in GitHub, push code to the repository and refer to the GitHub Actions tab in the repository to view all tests in the CI/CD pipeline that have passed or failed.
Manual testing is also possible by running the app (refer to User Documentation) and performing tests, such as button functionality tests, in the app environment.

### How to Add New Tests
If a test is specific to a created fragment (e.g., Buddy, Shop, Entries), create a new test under that test class. Currently, we only have basic tests, so a more advanced test would require the creation of a new advanced test file for that fragment. If the test is not specific to a fragment, create

 a new test file with a descriptive name (e.g., navigation tests, currency tests, etc.). No particular test harness is defined. Tests themselves should be named "test" + \[what is being tested\] + \[with what condition/expected outcome\].

<p align="right">(<a href="#readme-top">back to top</a>)</p>
