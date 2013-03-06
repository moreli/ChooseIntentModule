ChooseIntentModule
==================

This is a small Andriod Custom Intent Chooser module you can use together with [Titanium Appcelerator](http://appcelerator.com) on an Android device.

Build prerequisites
-------------------

To build the module, there are some dependencies which need to be fulfilled:

- Git
- Titanium Mobile SDK 2.1.3.GA or above
- Python >= 2.5
- Sun Java SDK 6.0
- Android SDK with Google APIs and SDK version 8 installed
- Android NDK Version 8D or above installed
- Ant >= 1.7.1


Build the module
----------------

To build the module, you need to clone our git repository and build the sources with ant:

    # clone the repository
    git clone https://github.com/moreli/ChooseIntentModule
    cd ChooseIntentModule
    # run ant to build the module
    ant clean && ant

If the build fails, make sure the entries in the buld.properies file point to the right path:

	titanium.platform=~/Library/Developer/Titanium/mobilesdk/osx/2.1.3.GA/android
	android.platform=~/Library/Developer/Android/platforms/android-8
	google.apis=~/Library/Developer/Android/add-ons/addon-google_apis-google-8
	android.ndk=~/Library/Developer/Android/android-ndk-r8d

The newly created *.jar and *.zip files can be found in the dist directory. The module you need is called com.abp.chooseintent-android-$VERSION.zip

Use the module
--------------

- Build the module or download is from the download section
- Place it into your appcelerator project directory
- Add the module to the module section of your tiapp.xml file: <modules><module version="1.0">com.abp.chooseintent</module></modules>
- Use it in your application with require('com.abp.chooseintent');
- Build your project as usual with the Titanium Developer application