# Domotique
A small smart home application, able to steer devices such as lights, roller shutters, or heaters.


# Application description

Fetch and parse a json file with all the data.  
List the devices of one or more selected device types.   
Deleting option on any devices.  
A user profile page where we can update all the informations. Fields are handled with an error management (email pattern, empty field).  
Steering page for each device type :  
-Lights: Mode ON/OFF and intensity management (0 - 100).  
-Roller shutters: Set position using a vertical slider (0 - 100).  
-Heaters: Mode ON/OFF and set the temperature with a step of 0.5 degrees (min: 7°, max 28°).  

# Technical stack  
  
Kotlin  
MVVM  
Translations (French and English)  
GIT  
Gradle  
Room  
Coroutines  
Retrofit  
Lottie  
