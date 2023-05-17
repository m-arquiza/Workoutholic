<a name="doc-top"></a>
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#description">Description</a></li>
    <li>
      <a href="#how-to-install">How to Install</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation-via-repo">Installation via repo</a></li>
        <li><a href="#installation-via-apk">Installation via APK</a></li>
      </ul>
    </li>
    <li><a href="#how-to-run">How to run</a>
      <ul>
        <li><a href="#run-via-repo">Run via repo</a></li>
        <li><a href="#run-via-apk">Run via APK</a></li>
      </ul>
    </li>
    <li><a href="#how-to-use">How to use</a></li>
    <li><a href="#submitting-a-bug-report">Submitting a bug report</a></li>
    <li><a href="#known-bugs">Known bugs</a></li>
  </ol>
</details>

<!-- A high-level description. What does the system do and why would a user want to use it. -->
# Description
Workoutholic is a dynamic fitness app that goes beyond conventional workout tracking by incorporating an entertaining mini-game experience. Our primary focus is to assist users in achieving their workout goals while ensuring they stay engaged and motivated throughout their fitness journey. For the workout portion of the app, we offer a workout logger that has selection from hundreds of workouts from our database for the user to track their workout. We also offer a workout statistic interface that shows graphical analysis of a user’s workout progress. For the gaming portion of the app, the user is able to use the currency that they have accumulated from working out in our dog mini-game. In the game, the user will take care of a dog named Shiba, who has hunger, thirst, and happiness status bars. The user is able to exchange the currency for food and water for Shiba, as well as additional items found in the store interface. Through our research, we found that the user retention rate for fitness apps are very high. We feel that with the combination of fitness and gaming, through our engaging mini game, it will help retain the user to continue using the app and better themselves physically and mentally.

<p align="right">(<a href="#doc-top">back to top</a>)</p>

<!-- How to install the software. If your system has prerequisites (e.g., tools, libraries, emulators, third-party applications, etc.), your instructions should list all of them and indicate how to install and configure them. Make sure to indicate what specific version requirements these prerequisites must satisfy. If running the system requires the installation of, e.g., a virtual machine, a database, or an emulator, make sure to provide clear step-by-step instructions. -->

<!-- How to install -->
## How to Install
### Prerequisites
1. Install Android Studio

### Installation via repo
1. Clone the repo
   ```sh
   git clone https://github.com/m-arquiza/Workoutholic.git
   ```

<p align="right">(<a href="#doc-top">back to top</a>)</p>

### Installation via APK
1. Install APK from release tab

<p align="right">(<a href="#doc-top">back to top</a>)</p>

<!-- How to run the software. How to start up the system? -->
## How to run
After installing Android studio and the emulator you would  be able to run it
### Run via repo
In Android Studio, You would need to first set up your configuration to run the app 
To do this you can create a new configuration.
1. Add new conifiguration
2. Select Android App
3. Choose module, in this case it would be "WorkoutholicApp.app.main"
4. Select Default APK for deployment options 
5. Select and/or choose your emulator
6. Run configuration

<p align="right">(<a href="#doc-top">back to top</a>)</p>

### Run via APK
In Android Studio you can run the APK
1. Click File
2. Press profile or debug APK
3. Select the APK that you downloaded 
4. Run APK on chosen emulator

<p align="right">(<a href="#doc-top">back to top</a>)</p>

<!-- How to use the software. You can assume that your user is familiar with your particular platform (e.g., use of a Web browser, desktop applications, or mobile applications). For missing functionality, your documentation should simply indicate that this functionality is work in progress. -->
## How to use
1. In the entries tab, you can log your workouts 
2. First you would select what muscle group you are working with 
3. Then you would select the exercise and the number of repetitions 
4. After completing the workout entry, you will get tokens which you can the use for your dog 
5. In the shops tab, you can buy food and toys for your dog
6. Then use those items in the buddy tab to keep your dog happy
7. Continue to log your workouts to get more tokens and keep your dog happy

<p align="right">(<a href="#doc-top">back to top</a>)</p>

<!-- How to report a bug. This should include not just the mechanics (a pointer to your issue tracker), but also what information is needed. You can set up a bug-report template in your issue tracker, or you can reference a resource about how to write a good bug report. Here is an example for bug reporting guidelines.
When the user has encountered a bug, they are encourage to contact any if not all of the contributors to our project, in which our contact information can be found on the github repository page. We are all available as soon as possible to help the user to resolve the bugs. -->
## Submitting a bug report
To submit a bug report, the user should follow the below guideline:
1. Steps to reproduce the bug: If the user can provide the steps to reproduce the bug, this can help the programmers trace back to the beginning and effectively walk through the steps to find the location of the bug.
2. Expected behavior vs. Actual behavior: Writing down the expected with actual behavior report can help the programmer understand the intended functionality, and compare it with the actual behavior.
3. Specific type of bugs: Whether the program crashed, is slow or non-responsive, knowing the specific type of bugs can direct the programmers to the actual problem quicker. 
4. Any additional information.

<p align="right">(<a href="#doc-top">back to top</a>)</p>

<!--Known bugs. Known bugs or limitations should be documented in the bug tracker. A user testing the implemented use case(s) should not encounter trivial bugs (e.g., NPEs) or a large number of bugs that are unlisted in your bug tracker. -->
## Known bugs
Limitations: This app is only usable on Android devices, as we are using Android Studio for our implementation. 

<p align="right">(<a href="#doc-top">back to top</a>)</p>
