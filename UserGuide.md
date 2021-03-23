# User Guide

## Installation
Get the apk into your device. User can copy it from pc or directly download in android device.
Then locate the apk in storage. It will be available in download folder if downloaded in android device.
Flow below steps to Install
1. Tap on `Zebra -v1.0.15-debug.apk` to install.
2. Then tap on `Install` button and wait.
3. Next tap on `Open` button. 

>Note: If app is not installed in this process then or else next step can be skipped
 ## Resolve debug apk installation problems
 1. Allow app installation from unknown resource in settings.       
 2. GooglePlayProtect also stops to install debug build. To fix that follow below steps.
    1. On your Android phone or tablet, open the Google Play Store app .
    2. Tap Menu Play Protect Settings .
    3.  Turn Scan apps with Play Protect on or off.
## App overview
At the initial point this app will ask for storage permission to save accelerometer data in CSV file. 
Then in dashboard this app has 4 buttons.
1. Accelerometer
2. Service
3. Export Data
4. Database

### Accelerometer
In this screen we have two graphs
1. Accelerometer data of x,y,z directions
2. Calculated TSV value from x,y,z value

### Service
This screen is for start the service for recording accelerometer data into database. We have 3 different options for frequency. 
1. Low ~5/sec
2. Mid ~15/sec
3. High ~50/sec
Then we have a button `Start Lifetime service` to start the service. How ever the service will also start on Power disconnect and Reboot.

### Export Data
Tap on this button to export accelerometer data in a CSV file. Then saved file location will be show in Toast massage. We have taken the storage permission for this at the beginning of the app.

### Database
This screen is to just verify the accelerometer data recoding is happening in background service. It have Two buttons.
1. Get all event
2. Last timestamp

### Extras
1. Service will stop on Power connect.
2. Service will start on Power disconnect and Reboot.
