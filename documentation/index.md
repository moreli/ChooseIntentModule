# chooseintentmodule Module

## Description

This module allows to build customer Intentchooser for android, based on the installed packages.
The module allows to verify if a certain pacakge is installed and 

## Accessing the chooseintentmodule Module

To access this module from JavaScript, you would do the following:

	var chooseintentmodule = require("com.abp.chooseintent");

The chooseintentmodule variable is a reference to the Module object.	

## Reference

TODO: If your module has an API, you should document
the reference here.

### ___PROJECTNAMEASIDENTIFIER__.isPackageAvailable([object])

Returns if a package is availeble for a given object.

The object will use the same syntax of the Titanium Intent documentation for [Android Intents](http://docs.appcelerator.com/titanium/2.1/#!/api/Titanium.Android.Intent)

For Example:

	var intent = {
		action : intentField.value,
		type : "text/plain",
		packageStr : 'twitter'
	};
	
Note: Use packageStr as the string parameter for the Intent to be found.

### ___PROJECTNAMEASIDENTIFIER__.getPackages([object])

Returns an array of all packages meeting the critiria of [object]

### ___PROJECTNAMEASIDENTIFIER__.addIntentToChooser([object])

Adds intent (of syntax [object]) to the list of Intents that will appear in the intent chooser. 

### ___PROJECTNAMEASIDENTIFIER__.removeIntentFromChooser([string])

Remove the intent of with packageName [string] from the list of Intent chooser.

### ___PROJECTNAMEASIDENTIFIER__.emptyChooser()

Empty the Intent Chooser List.

### ___PROJECTNAMEASIDENTIFIER__.showChooser()

Display the Intent Chooser List.

### ___PROJECTNAMEASIDENTIFIER__.putExtra()

Similar to [putExtra](http://docs.appcelerator.com/titanium/2.1/#!/api/Titanium.Android.Intent-method-putExtra) Titanium Intent

### ___PROJECTNAMEASIDENTIFIER__.putExtraUri()

Similar to [putExtraUri](http://docs.appcelerator.com/titanium/2.1/#!/api/Titanium.Android.Intent-method-putExtraUri) Titanium Intent

## Usage

Refer to app.js in the example folder

## Author

Eli Mor

## License

TODO: Free
