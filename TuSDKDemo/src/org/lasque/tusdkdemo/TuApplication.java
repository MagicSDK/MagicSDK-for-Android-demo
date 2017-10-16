/** 
 * TuSDK
 * TuApplication.java
 *
 * author 		Clear
 * Date 		2014-11-18 PM 5:14:34 
 * Copyright 	(c) 2014 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo;

import com.tencent.bugly.crashreport.CrashReport;

import org.lasque.tusdk.core.TuSdk;
import org.lasque.tusdk.core.TuSdkApplication;

/**
 * Application Object
 * 
 * @author Clear
 */
public class TuApplication extends TuSdkApplication
{
	/** Create application */
	@Override
	public void onCreate()
	{
		super.onCreate();

		/**
		 ************************* Three Steps To Integrate With TuSDK *************************
		 * 
		 * 1. Register a developer account in official website
		 * 
		 * 2. Download SDK and sample project
		 * 
		 * 3. Create application, get appkey and resource files
		 * 
		 ************************* Three Steps To Integrate With TuSDK ************************* 
		 * 
		 * About TuSDK's Size (About 2M)
		 * 
		 * Contents about Android compiling：
		 * APK file includes java source code, JNI library and resource files;
		 * JNI library includes different CPU architectures, such as arm64-v8a,armeabi and so on. All of these will be packaged into APK;
		 * When you install an apk, the system will automatically choose the most suitable JNI architecture, other architectures don't occupy space;
		 * Excluding resource files and JNI library, TuSDK's size is about 2M.
		 * 
		 * Development Document: http://tusdk.com/doc
		 */
		
		/** Initialize Bugly SDK */
		CrashReport.initCrashReport(getApplicationContext(), "a0f34b9a81", true); 

		/** 
		 * Set resource class when application id is not equal to package name, 
		 * must be called before initialize TuSDK 
		 */
		TuSdk.setResourcePackageClazz(org.lasque.tusdkdemo.R.class);
		
		/** Customize so files path before SDK initialization */
		// NativeLibraryHelper.shared().mapLibrary(NativeLibType.LIB_CORE, "libtusdk-library.so file path");
		// NativeLibraryHelper.shared().mapLibrary(NativeLibType.LIB_IMAGE, "libtusdk-image.so file path");

		/** Enable log, will be convenient to location problems*/
		this.setEnableLog(true);

		/**
	     *	Initialize TuSDK
	     *	
	     *	Your appkey, package name and resource files should be one-to-one correspondence with each other, 
	     *	otherwise your application will throw errors.
	     *
	     *  @param appkey (Get appkey at http://tusdk.com )
	     */
		this.initPreLoader(this.getApplicationContext(), "895ffc71882dbcc9-06-313kn1");
		
		// TODO TODO 
		/**
	     *  Specify development mode, devType should match with master key in the lsq_tusdk_configs.json file, 
	     *  if devType is null, application read the master key by default.
	     *  If an application have several package names, you can use this way to integrate and debug with TuSDK.
	     */
//		this.initPreLoader(this.getApplicationContext(), "12aa4847a3a9ce68-04-ewdjn1", "debug");

		// If you don't want to extend TuSdkApplication, you can use the following way in Application.onCreate() method:
		/** Enable logs */
		// TuSdk.enableDebugLog(true);
		/** Development ID (Please go to http://tusdk.com to get appkey) */
		// TuSdk.init(this.getApplicationContext(), "12aa4847a3a9ce68-04-ewdjn1");
		/** Specify development mode */
		// TuSdk.init(this.getApplicationContext(), "12aa4847a3a9ce68-04-ewdjn1", "debug");
	}
}