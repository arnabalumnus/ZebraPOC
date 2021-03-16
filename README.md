# ZebraPOC

## Getting started

This app is used for detecting Throw or Free fall of Android device using accelerometer. This whole process is done in background. 
Accelerometer data of X, Y and Z direction data saved with time stamp.

## Service
* EventTrackingService
* LifeTimeService
* SaveCsvJobService

## Broadcast Receiver
* BootReceiver
* PowerConnectionReceiver

## Database Tables
* accelerometer_log
* event_log
* log

#Utils
* ExportFile
* DateFormatter
* TimeSpan Enum

## TODO Planing 
* For delete old record in csv.
* Detect Event using Machine Learning in Android in background.
