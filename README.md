# TeamLaughingTribble
CS 160 Team Project
By: Alvin Ma, Byron Wang, Jennifer Nghi Nguyen, Marco Sandoval

<b>Note: Update README assignment 5: All work are done within Android repo. No changes to backend repo <br/></b>

Login infomation on Android:<br/>
username: laughingtribble@gmail.com<br/>
pass: laughingtribbleteam<br/>


<b>Note: Update README assignment 4: in assignment 4 all the tests are carried out in Client (Android) project; backend project remains untouched. <br/></b>

1. Client (Android) <br />
2. Backend <br />
3. Testing<br />


<br />
1. Client (Android)
	Github: https://github.com/alvinma/TeamLaughingTribble
To run the application, we used the Android IDE version 2.3.3 to program the application and the emulator of the IDE to emulate a simulation of the application. We chose to use version 2.3.3 instead of version 3.0 because we were unsure of how stable 3.0 is and we did not want to take on any unnecessary bugs that would come with the new large update. Within Android IDE, we were given the option to install SDK tools to help give us options of more features to put in our application. 

The SDKs we chose to install for the purpose of our application were:  
Android Emulator v. 26.1.4
Android SDK Tools v. 26.1.1
Google Play services v. 46
Intel x86 Emulator Accelerator (HAXM installer) v. 6.2.1
Please make sure your android API level is at least 25 to support the shortcut features.

After the installations, import the ParkHere application into Android Studio. This will allow the IDE to compile and run the project for you when you hit the “run app” drop down selection under the “run” tab. This will prompt an application window, allowing you to choose a phone type to emulate the application on.

In the app, you need sign up using a gmail account to get started.

With the application running, you can start using the application on the emulation window. In our current version of the application, the user will enter with the login page. Once passed the login page, the user will be directed to a list of parking places currently available in San Jose. From here, the user will be able to either submit a parking spot or select a parking spot to book. 

<br/>
<a href="https://postimages.org/" target="_blank"><img src="https://s26.postimg.org/mnlsd9bo9/Screen_Shot_2017-11-05_at_4.05.01_PM.png" alt="Screen_Shot_2017-11-05_at_4.05.01_PM"/></a><br/><br/>
<a href="https://postimages.org/" target="_blank"><img src="https://s26.postimg.org/yo78dzj2x/Screen_Shot_2017-11-05_at_4.05.13_PM.png" alt="Screen_Shot_2017-11-05_at_4.05.13_PM"/></a><br/><br/>

2. Backend
Note that in repo Backend.zip or : https://github.com/jennifernghi/TeamLaughingTribbleBackEnd is our planned backend repo. However, up until this point, we only focus on firebase authentication and CRUD in the front end project. So, this project is just a environment setup so far.
To use the ParkHere Android Application, you do not need to set up a backend service. We used the Google Cloud Platform + Firebase to handle the backbone of our infrastructure. Doing so allowed us to focus on building the Android application rather than focusing on server set up & maintenance and creating an API to handle requests from the clients. Since Firebase handles client-server communication and queries on the Database, our backend is primarily the environment with the Realtime Database.

The Firebase Android API provides queries CRUD operations on the Realtime Database from the Android Application. The Google Cloud platform allows us to extend commonly used services such as: User Authentication, NoSQL Database, Notifications, Cloud File Storage, and Cloud Testing. 

To view the backend/database live:
Log into https://parkherefirebasebackend-94efb.firebaseio.com/
You can log into the FireBase console with our team gmail account.
username: laughingtribble@gmail.com
pass: laughingtribbleteam
The view should be the root of the database.
Click on any node to expand it and view the information it contains.
Done.

To set up FireBase on an Android Application you must:
Set up a Gmail account.
Log into https://console.firebase.google.com/
Follow the onscreen instructions:
Link to the instructions: https://firebase.google.com/docs/android/setup
Done.

You will need following installation
Node.js: brew install node <br/>
Firebase CLI: npm install -f firebase-tools <br/>

After finising the installation, go to the project folder and login with firebase account (laughingtribble@gmail.com)
in terminal: firebase login

To deploy: firebase deploy. 

...
Project Console: https://console.firebase.google.com/project/parkherefirebasebackend-94efb/overview
Function URL (getAllPosts): https://us-central1-parkherefirebasebackend-94efb.cloudfunctions.net/getAllPosts
Function URL (getAllPlaces): https://us-central1-parkherefirebasebackend-94efb.cloudfunctions.net/getAllPlaces
Above is all the fuctions we have so far in this project

3. Testing - Android repo <br />
White-box unit tests<br />
All whitebox tests locate in the package: /app/src/test/java/edu/sjsu/thelaughingtribble/parkhere (test)<br />

Run the tests: <br />
Simply right-click on the test class and select Run <testclass> with coverage<br />
Or, each sub tests can be run individually by select a test in a class, right-click and select run <test method> with coverage<br /><br />
Black-box instrument test<br />
All blackbox instrumentation tests locate in the package: app/src/androidTest/java/edu/sjsu/thelaughingtribble/parkhere (androidTest)<br /><br />

Run the tests: <br />
Simply right-click on the test class and select Run <testclass> <br />
Or, each sub tests can be run individually by select a test in a class, right-click and select run <test method> 
Select a simulator to run the test<br /><br />

Get overall code coverage using Jacoco plugin<br />
We can get overall code coverage using jacoco plugin (this plugin will test all instrumentation test all at once)
In Android Studio terminal:  ./gradlew createDebugCoverageReport<br />
After the process finish, the html report “index.html” can be found at this path: TeamLaughingTribble/ParkHere/app/build/reports/coverage/debug<br />


