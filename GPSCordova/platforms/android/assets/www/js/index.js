/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
      this.onDeviceReady();
      //this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
      //document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.initApp();
    },
    // Update DOM on a Received Event
    initApp: function() {
        this.initbt = document.getElementById("initbt");
        this.longitude = document.getElementById("long");
        this.latitude = document.getElementById("lat");
        this.lastUpdate = document.getElementById("last");
        initbt.addEventListener("click", this.click);
    },
    // Clicked me
    click: function() {
    	if (app.watchId == undefined) {
    		navigator.geolocation.getCurrentPosition(app.onPosChange,app.onPosError);
    		app.watchId = navigator.geolocation.watchPosition(app.onPosChange,app.onPosError,{timeout: 5000, enableHighAccuracy: true });
    		this.innerHTML = "Detener";
    	}
    	else {
			navigator.geolocation.clearWatch(this.watchID);
			app.watchId = undefined;
			this.innerHTML = "Iniciar";
		}
    },
    onPosChange: function(position) {
		app.longitude.innerHTML = position.coords.longitude;
		app.latitude.innerHTML = position.coords.latitude;
		app.fecha = new Date();
		app.lastUpdate.innerHTML = app.fecha.getHours() + ":" + app.fecha.getMinutes() + ":" + app.fecha.getSeconds();
	},
	onPosError: function(error) {
		alert("[Error "+ error.code + "] " + error.message);
	}
};


app.initialize();