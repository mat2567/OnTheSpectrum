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



========= CALLING SERVICE
The calling service in our application is developed in Python and the service is provided by Plivo.
The Python script integration is made possible through 
