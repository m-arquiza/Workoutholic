# DEVELOPER DOCUMENTATION


## Obtaining Source Code
Our system files are all containe within one repository. Closing the "m-arquiza/Workoutholic" repository enables access to all source code for our Workoutholic App.


## Directory Layout
TBD


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
TBD


## Testing
TBD

### How to Add New Tests
If a test is specific to a created fragment (ex. Buddy, Shop, Entries), create a new test under that test class. Currently, we only have basic tests, so a more advanced test would necessitate a creation of a new advanced test file for that fragment. If the test is not specific to a fragment, a new test file with a descriptor should be created (ex. navigation tests, currency tests, etc.). No particular test harness defined. Tests themselves should be named "test" + \[what is being tested \] + \[with what condition/expected outcome\].
