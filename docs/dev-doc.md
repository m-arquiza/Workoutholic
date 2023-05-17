# DEVELOPER DOCUMENTATION


## Obtaining Source Code
Our system files are all containe within one repository. Cloning the "m-arquiza/Workoutholic" repository enables access to all source code for our Workoutholic App.


## Directory Layout
.github/workflows, .idea, gradle/wrapper contain files related the gradle and the CI/CD pipeline\
app contains all code for the app\
    src directory (Workoutholic/app/src) contains test and source code files\
    Workoutholic/app/src/main/java/com/example/workoutholicapp contains backend and frontend code for the buddy, entries, and shop screens\
docs contains user and developer documentation\
reports contain weekly reports from the team on our progress



## Building
1. Prerequisites
    1. Compatible JDK version is installed
    2. Development environment that supports Android app developement (Android Studio preferred) is installed
    3. Repository is cloned and launched in Android Studio
2.  Building
    1. On left sidebar, click the project tab
    2. Expand the "Gradle Scripts" folder, then open the "build.gradle" file for the app (ex. termed "build.gradle (Module: app))
    3. If a notification pops up at the top asking to project sync gradle files, click "Sync now" 
    4. Underneath the navigation ribbon, next to the forward and back buttons, click the green hammer "Make Project" hammer symbol
        a. If it does not exist/is highlighted, ensure that the "Run/Debug Configuration" is set to "app"
    
### How to Build a Release
Gradle and the application might not work effectively if you do not have Java Version 17 is installed on your computer before running the application, so update that version number if necessary/if issues are encountered while building the release.


## Testing
### How to test the software
If testing in an Android app development environment, run the tests that are named after the class for which you would like to test. (For example, if you would like to test the shop functionalities, run the tests under BasicShopTests.java) If testing the entire software, run the tests on every class.\ 
If testing in GitHub, push code to the repository and refer to the GitHub Actions tab in the repository to view all tests in the CI/CD pipeline that have passed/have failed. \
Manual testing is also possible by running the app (refer to User Documentation) and performing tests, such as button functionality tests, in the app environment. 

### How to Add New Tests
If a test is specific to a created fragment (ex. Buddy, Shop, Entries), create a new test under that test class. Currently, we only have basic tests, so a more advanced test would necessitate a creation of a new advanced test file for that fragment. If the test is not specific to a fragment, a new test file with a descriptor should be created (ex. navigation tests, currency tests, etc.). No particular test harness defined. Tests themselves should be named "test" + \[what is being tested \] + \[with what condition/expected outcome\].
