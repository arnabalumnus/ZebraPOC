# Zebra Device Usage 

## Getting started
This app is used for detecting Throw or Free fall of Android device using accelerometer. This whole process data collections from accelerometer is done in background. 
Accelerometer data of X, Y and Z direction saved with corresponding timestamp.

With the help of this data and using data analysis/engineering this app detects different events.


## Service
* EventTrackingService
* LifeTimeService
* SaveCsvJobService

#### LifeTimeService
It is a foreground service. It's runs in background with a foreground notification. In background this service runs 24x7 collects
Accelerometer data and save into database table called accelerometer_log.


## Broadcast Receiver
* BootReceiver
* PowerConnectionReceiver

#### BootReceiver
It detects System reboot and restarts `LifeTimeService`. 

#### PowerConnectionReceiver
It detects an event while device connects to a power source and disconnect from the power source.
Based on power connect event device starts exporting `accelerometer_log` table data into a CSV file and delete exported records from database.



## Database Tables
* accelerometer_log
* event_log
* log

#### accelerometer_log
|ts  |X    |Y    |Z  |
|----|-----|-----|---|
|1616070150276|0.0|	9.776309967041016|	0.8123490214347839|
|1616070150298|	0.8123490214347839|0.0|9.816309967041016|
|1616070150319|9.816309967041016|0.8123490214347839|0.0|

>**ts : Long**  *TimeStamp in millisecond*    
>**X  : float**  *x coordinate values*     
>**Y  : float**  *y coordinate values*     
>**Z  : float**  *z coordinate values*          

## Utils
* ExportFile
* DateFormatter
* TimeSpan Enum

## Pending plans or TODO
* For delete old record in csv.
* Detect Event using Machine Learning in Android in background.


## Device File Explorer
```
parent/root
├── data
|   └── com.alumnus.zebra
|           ├── cache
|           ├── code_cache
|           ├── database
|           |       └── database-name
|           └── shared_prefs
|                   ├── Zebra.xml
|                   └── ZebraSp.xml
├── ...
|
└── sdCard
       ├── Android
       |    ├── data 
       |    ├── media
       |    └── obb
       |
       ├── ...
       |
       └── ZebraApp
               ├── csvData
               └── logs
```