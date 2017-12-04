# On the Spectrum #

## Release Notes (version 1.0) ##
* Product Overview
  * Create, edit, and delete profile fields
  * Create, edit, and delete emergency scenarios
  * Call emergency services
  * Get location of mobile device
  * Hear the message that will be said during the call
  * Customize color scheme
  * Customize font size
  * Lock profile and emergency scenario customization
  * Reset all customized features to the application’s default
  * Access call log that details how the application has been used
  * Clear call log
  * Text a caretaker with information about the emergency including Google Maps link
* Bug Fixes: 
  * We fixed the fact that the application was not previously in existence.
* Known bugs and defects:
  * The server may voice both old and new information to emergency services if calls are placed to close in time to each other.
  * For the caretaker phone number to be texted and read to emergency services specifically as a method of contacting the caretaker, the field must be called, “Caretaker Phone Number”. Otherwise, it’s read as any other profile element.
  * The phone number stored may be read as a long number, which includes the words “million”, “thousand”, “hundred”, etc.
  * The slide button that shows whether customization is locked is not functional.


## Install Guide ##
* Pre-requisites
  * Mobile application with Android OS (minimum API 16)
  * Phone Service/ Phone carrier
  * Email to receive app download
  * At least 2MB storage space on device
* Download instructions
  * Locate and download the file called “app-universal-debug.apk” found in the project’s repository.
  * Email this .apk file to the person who wishes to download the application.
  * Using an Android phone’s email application, open up the email containing the .apk and, when prompted, install the application.
  * Since this device is not installed through the Play Store you may be prompted to verify that you want to download the file. Select “yes” or whatever affirmative action is available. 
  * If a prompt appears on your screen warning about downloading from “unknown sources” you need to allow such downloads by navigating to Settings >> Lock Screen and Security Unknown Sources>> toggle the button to the on position.  (On older generations this can be found Settings >> Applications >> Unknown sources, or by searching for 'unknown sources' and turning it on.)
  * The app should show up on the device when it is fully installed.
* Run instructions
  * Locate the application within the phone and press its icon.
* Additional Notes
  * The application will contact emergency services without the user providing permissions for location and texting. However, is strongly recommended that the user allows the application to use location services because it is information that is needed by to emergency services. Texting is recommended to allow the application to automatically update the user’s caretaker.
* Troubleshooting
  * If the user denied texting or location permissions and wants to allow them:
    * Navigate to “Settings” on the device.
    * From the application list within Settings, select “On the Spectrum”.
    * Navigate to “App Permissions”. 
    * Allow “Location” and/or “Texting” permissions.
