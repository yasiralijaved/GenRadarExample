/*
 * Copyright (C) 2013 Yasir Ali Javed
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.yasiralijaved.genradarexample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.yasiralijaved.genradar.main.GenRadarManager;
import com.yasiralijaved.genradar.main.GenRadarPoint;

/**
 * @author Yasir.Ali
 *
 */
public class MainActivity extends Activity {

	/*
	 * Step-1
	 * Introduce the Essential GenRadar Objects
	 */
	
	// GenRadar API
	private GenRadarManager mGenRadarManager;
	private List<GenRadarPoint> mGenRadarPoints;
	private GenRadarPoint mCentralGenRadarPoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 * Step-2
		 * Create a method initGenRadar() and call it for Radar Initialization Process
		 */
		initGenRadar();
		
	}
	
	/*
	 * Step-3
	 * This method is called once in the onCreate() Method of Activity
	 * The Purpose of this method is to initiate the One-Time Radar Creation Process
	 */
	private void initGenRadar() {

		// Generate the Radar Points
		mGenRadarPoints = generateGenRadarPoints();
		
		// Create the central Radar Point which will be used by GenRadar API
		mCentralGenRadarPoint = new GenRadarPoint("Center Point", 33.683232, 72.988972, 0, 0, 1.2f, Color.TRANSPARENT);
		
		// Initialize the GenRadarManager
		// The last two params are same as declared width and height of container and top parent layout in xml layout file
		mGenRadarManager = new GenRadarManager(MainActivity.this, (LinearLayout) findViewById(R.id.container), 120, 120);

		// Let the GenRadarManager to do the radar-initialization process
		mGenRadarManager.initAndUpdateRadarWithPoints(mCentralGenRadarPoint, mGenRadarPoints);

	}
	
	/*
	 * Step-4
	 * @return The list of GenradarPoint to be populated on Radar
	 * @see GenRadarPoint
	 */
	private List<GenRadarPoint> generateGenRadarPoints(){
		List<GenRadarPoint> genRadarPoints = new ArrayList<GenRadarPoint>();

		// Default value of radius for a Radar Point
		float radius = 1.2f;
		
		// Model Filling Station
		genRadarPoints.add( new GenRadarPoint("Model Filling Station", 33.685354, 72.985651, 0, 0, radius, Color.BLUE) );

		// IMCB
		genRadarPoints.add( new GenRadarPoint("IMCB", 33.688210, 72.991315, 0, 0, radius, Color.BLUE) );

		// UnKnown
		genRadarPoints.add( new GenRadarPoint("UnKnown", 33.684854, 72.991315, 0, 0, radius, Color.BLUE) );

		// Shifa medical Center
		genRadarPoints.add( new GenRadarPoint("Shifa medical Center", 33.683836, 72.986573, 0, 0, radius, Color.BLUE) );

		// Alian Enterprises
		genRadarPoints.add( new GenRadarPoint("Alian Enterprises", 33.681399, 72.990545, 0, 0, radius, Color.BLUE) );

		// Sadar police Station
		genRadarPoints.add( new GenRadarPoint("Sadar police Station", 33.691424, 72.970287, 0, 0, radius, Color.BLUE) );

		return genRadarPoints;

	}
	
	/*
	 * Step-5
	 * (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {		
		super.onResume();
		// Start GenRadarManager Processing
		// This is mandatory to register the GenRadarManager
		if(mGenRadarManager != null){
			mGenRadarManager.registerListeners();
		}
	}
	
	/*
	 * Step-6
	 * (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// Stop GenRadarManager Processing
		// This is mandatory to unregister the GenRadarManager for batter saving
		if(mGenRadarManager != null){
			mGenRadarManager.unregisterListeners();
		}
		super.onPause();

	}

}
