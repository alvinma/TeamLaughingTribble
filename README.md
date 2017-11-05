# TeamLaughingTribble
CS 160 Team Project
By: Alvin Ma, Byron Wang, Jennifer Nghi Nguyen, Marco Sandoval

Client (Android)

To run the application, we used the Android IDE version 2.3.3 to program the application and the emulator of the IDE to emulate a simulation of the application. We chose to use version 2.3.3 instead of version 3.0 because we were unsure of how stable 3.0 is and we did not want to take on any unnecessary bugs that would come with the new large update. Within Android IDE, we were given the option to install SDK tools to help give us options of more features to put in our application. 

The SDKs we chose to install for the purpose of our application were:  
Android Emulator v. 26.1.4
Android SDK Tools v. 26.1.1
Google Play services v. 46
Intel x86 Emulator Accelerator (HAXM installer) v. 6.2.1

	After the installations, import the ParkHere application into Android Studio. This will allow the IDE to compile and run the project for you when you hit the “run app” drop down selection under the “run” tab. This will prompt an application window, allowing you to choose a phone type to emulate the application on.

	With the application running, you can start using the application on the emulation window. In our current version of the application, the user will enter with the login page. Once passed the login page, the user will be directed to a list of parking places currently available in San Jose. From here, the user will be able to either submit a parking spot or select a parking spot to book. 



2. Backend

To use the ParkHere Android Application, you do not need any setup. We used the Google Cloud Platform + Firebase to handle the backbone of our infrastructure. Doing so allowed us to handle Realtime Database queries from the Android Application while having 24/7 server uptime. 

The Google Cloud platform allows us to handle commonly used services such as: User Authentication, NoSQL Database, Notifications, Cloud File Storage, and Cloud Testing.

To view the backend/database live, you can log into the FireBase console with our team gmail account.

username: laughingtribble@gmail.com
pass: laughingtribbleteam


To set up FireBase on an Android Application you must:
Set up a Gmail account.
Log into https://console.firebase.google.com/
Follow the onscreen instructions

