/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 * 
 * got help from 
 * http://stackoverflow.com/a/8550043 
 * and A LOT from
 * https://github.com/aaronksaunders/TiCardIO.Android
 * 
 */
package com.abp.chooseintent;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.IntentProxy;
import org.appcelerator.titanium.TiApplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;

@Kroll.module(name = "Chooseintentmodule", id = "com.abp.chooseintent")
public class ChooseintentmoduleModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "ChooseintentModule";
	private static String packageStr = "";
	private List<Intent> chooserIntents = new ArrayList<Intent>();

	public ChooseintentmoduleModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is
		// created
	}

	@Kroll.method
	public boolean isPackageAvailable(HashMap args) {
		Log.d(LCAT, "isPackageAvailable: " + args.toString());
		if (args.containsKey("packageStr"))
			packageStr = args.get("packageStr").toString();
		else
			return false;

		final PackageManager packageManager = TiApplication.getInstance()
				.getApplicationContext().getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(
				handleCreationArgs(args), PackageManager.MATCH_DEFAULT_ONLY);

		if (!list.isEmpty()) {
			for (ResolveInfo info : list) {
				if (info.activityInfo.packageName.toLowerCase().contains(
						packageStr)
						|| info.activityInfo.name.toLowerCase().contains(
								packageStr))
					return true;
			}
		}
		return false;
	}
	
	@Kroll.method
	public Object[] getPackages(HashMap args) {
		Log.d(LCAT, "getPackagesForAction for action " + args.toString());

		List<String> packages = new ArrayList<String>();
		final PackageManager packageManager = TiApplication.getInstance()
				.getApplicationContext().getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(
				handleCreationArgs(args), PackageManager.MATCH_DEFAULT_ONLY);

		if (!list.isEmpty()) {
			for (ResolveInfo info : list) {
				if (info.activityInfo.packageName.toLowerCase().contains(
						packageStr)
						|| info.activityInfo.name.toLowerCase().contains(
								packageStr))
					packages.add(info.activityInfo.packageName);
			}
		}
		return packages.toArray();
	}
	
	@Kroll.method
	public void addIntentToChooser(HashMap args) {
		Log.d(LCAT, "addIntentToChooser: " + args.toString());
		chooserIntents.add(handleCreationArgs(args));
	}
	
	@Kroll.method
	public void removeIntentFromChooser(String packageName) {
		int index = -1;
		Log.d(LCAT, "removeIntentFromChooser: " + packageName);
		for (Intent intent : chooserIntents) {
			Log.d(LCAT, "pacakge: " + intent.getPackage().toString());
			if (intent.getPackage().toString().equals(packageName))
				index = chooserIntents.indexOf(intent);
		}
		if (index != -1)
			chooserIntents.remove(index);
	}
	
	@Kroll.method
	public void emptyChooser() {
		Log.d(LCAT, "emptyChooser");
		chooserIntents.clear();
	}

	@Kroll.method
	public void showChooser(String title) {
		if (!chooserIntents.isEmpty()) {
			// workaround for when showchooser removes an element
			Intent intent = new Intent(chooserIntents.get(chooserIntents.size()-1));
			Intent chooserIntent = Intent.createChooser(chooserIntents.remove(chooserIntents.size()-1), title);
			chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, chooserIntents.toArray(new Parcelable[] {}));
			TiApplication.getInstance().getApplicationContext().startActivity(chooserIntent);
			chooserIntents.add(intent);
		}
	}
	
	@Kroll.method
	public void putExtra(String key, Object value) {
		if (!chooserIntents.isEmpty()) {
			Log.d(LCAT, "putExtra: " + key + " " + value.toString());
			IntentProxy intent = new IntentProxy(chooserIntents.get(chooserIntents.size()-1));
			intent.putExtra(key, value);
			chooserIntents.remove(chooserIntents.size()-1);
			chooserIntents.add(intent.getIntent());
		}
	}
	
	@Kroll.method
	public void putExtraUri(String key, String uri) {
		if (!chooserIntents.isEmpty()) {
			Log.d(LCAT, "putExtraUri: " + key + " " + uri);
			IntentProxy intent = new IntentProxy(chooserIntents.get(chooserIntents.size()-1));
			intent.putExtraUri(key, uri);
			chooserIntents.remove(chooserIntents.size()-1);
			chooserIntents.add(intent.getIntent());
		}
	}
	
	// using the titanium intent syntax the proxy will create an intent
	private Intent handleCreationArgs(HashMap args) {		
		if (args.containsKey("packageStr")) {
			packageStr = args.get("packageStr").toString();
			args.remove("packageStr");
		}

		KrollDict dict = new KrollDict(args);
		IntentProxy intent = new IntentProxy();
		if (!args.containsKey("action"))
			Log.d(LCAT,
					"No action specified. Action is required to check or create Intent");
		else {
			intent.handleCreationDict(dict);
		}

		return intent.getIntent();
	}
}
