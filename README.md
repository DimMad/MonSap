# MoneySapling
Android MoneyTree tech test.

The name is a joke pertaining to the actual platform.

The app is still very young, as such it is a young tree, hence a sapling...!!! (software engineer obviously) 

## Running the app
The application has a mockDebug variant please make sure it is chosen before running it. 
There is no prod flavor at this moment.

## The 4 pillars
### Understanding how you work
The application was designed while trying to use some of the latest and greatest of Android, namely Jetpack.

It is using the Navigation library to simplify screen navigation. As such the base architecture of the app is a single Activity with multiple Fragments. On the code architecture are the Google promoted MVVM is used. Currently due to the simplicity of the app and the time constraints, no effort is made to separate logic from view models so as to keep them cleaner. It is not a problem per se but it should be a consideration for a much larger platform.

On the UI front, the androidx material library is used. The library is the replacement of the old support libraries and pushes the boundaries  providing many improved ways to implement the material design. As such the current application is made basically white and as Android 10 is being targeted, it is not very difficult to add support for a dark mode by simply editing the proper styles.

Functions-wise there are right now several limitation due to the constraints. There are several ```TODO``` blocks throughout the code explaining such limitations and needed refactors/fixes.

From the user stories, numbers (1) and (2) were chosen, but parts from others can also be found. The main capability missing from the chosen user stories is the ability to show and properly calculate currency. Only the accounts list has proper currency formatting and that is not perfect.

### No warning
I would first like to say that personally, sometimes, I allow warnings as they can be good reminders. 

With that said I have tried to fix most warning and for the ones not fixed you will find comments in their locations.

The big exception is a huge amount of warnings concerning resources. I have been using a very nice list of the material palette found in Github. Two possible solutions exist for that. Either clear the unneeded ones, if you are a purist (defeats the purpose of the list), or add a lint XML file excluding them.

### Testing
Unit tests have been created. To run and see the coverage you can use the "run with coverage" function of Android Studio. The run configuration "moneysapling in app" has been added to the repo. It has also been configured to filter down as much as possible, out of test scope paths. Still a lot of things that cannot be unit tested are left making the coverage suffer. The reason they were left is to act as an overview and reminder for the instrumentation tests.

Instrumentation testing has not been written with one very simple exception which was created as a sample. "Future work". If you want to run it, a configuration is also provided.

### Accessibility
Due to the time limitation no endeavor has been made in this aspect. The system automatically can handle some types, like screen reading, but that is the extend of it.

With that being said, it is true that I do not have much experience as I have never had the chance to work with accessibility. However, I can say that a good design with good spacing and positioning of items, a inclusion of contrast and color blind modes (moot point with current design, it is the definition of contrast), and taking care of touch targets would a very good point to start at.
