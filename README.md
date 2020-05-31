# Location analyzer

This is a program to build the commute log from your Google location history. You can use the
 generated CSV file to analyze your commute patterns, but mostly for curiosity and fun! This only
  needs Java 8, or higher, to run.
  
  More information here: https://medium.com/@shobhit.shri/my-commute-pattern-38d952c77511

## Getting Started

### 1. Check if you have Java

Check Java by typing java -version on console, if you don't get an output like below, follow the
 guidelines online.
 
 ```shell
C:\Users\shobhit.shrivastava>java -version
java version "1.8.0_241"
Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
 ```

### 2. Get Your Location Data

Here you can find out how to download your Google data: <https://support.google.com/accounts/answer/3024190?hl=en></br>
Here you can download all of the data that Google has stored on you: <https://takeout.google.com/>

To use this script, you only need to select and download your "Location History", which Google
 will provide to you as a JSON file by default. This program only works with JSON and would give
  error if you try any other file.

#### 3. Run the script:

You can download the latest "commute-analyzer.jar" jar from the release tab of this repository
: https://github.com/shobhitshri/location-analyzer/releases


```shell
java -jar commute-analyzer.jar -i "C:\Users\your-name\Location History.json" -o C:\Users
\your-name\ -lah 12.739082 -loh 77.706344 -law 12.9794710 -low 77.6366410 -t IST -m 10000
```

##### Usage details:

```
usage: commute-analysis-1.0.jar [-i INPUT] [-o OUTPUT] 
                      [-loh YYYY-MM-DD] [-lah YYYY-MM-DD]
                      [-low YYYY-MM-DD] [-law YYYY-MM-DD]
                      [-m NUMBER][-t TimeZone]

arguments:
-i      input file             - Your location history JSON file from Google Takeout
-o      output directory       - Directory where you want to generate the output file
-loh    longitude home         - Logitude coodinates of your home from Google maps
-lah    latitude home          - latitude coodinates of your home from Google maps
-low    longitude work         - Logitude coodinates of your work from Google maps
-law    latitude work          - latitude coodinates of your work from Google maps
-m      Margin                 - [OPTIONAL] Used to find equality between location, keep large if
 you are getting too few results, default is 5000 but start with 10000. Reduce for more accuracy or
 shorter distances.
-t      time zone              - [OPTIONAL] Default is local time of the machine where it is run
 but you can give the time zone if the data is generated for some other zone like PST. Find the
 correct term for you from here: 
https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html#SHORT_IDS 
```

### 4. Review the Results

The program will generate a CSV file named `commute_log.csv` in the output location specified. Enjoy!

## FAQ

### How long does it take to complete?

It took me a minute to analyze 6 years of location history file of 45 MB size. Based on how fast
 your computer is and the size of the history file, it might take 10 minutes at most. Please
  report if it takes more than that.
 
### Why some rows in the results are totally unbelievable?
 That is the problem with how the Google location history is recorded and can't be fixed by this
  program, yet! However I would recommend to define the lowest and highest values for your
   commute and discord values outside this range. Like, for me, anything below 30 minutes and over
    120 minutes is irrelevant, so I manually deleted those rows before analysis.
    
### Why do I get such unbelievable row?

I noticed two reasons, but there might be more:
1. The phone runs out of charge while you are commuting so it records the start time from work
, but records the end time only when you have charged and started it back at home.
2. This is not really the wrong data. You start from home, or work, and do somethings on the way
 like visiting a pub or a dentist. It will still be recorded as commute and show much longer
  duration in the graph.
  
### What are the Limitations?
1. I am rolling over the day at 12 AM every night. What it effectively means is that it only
 works when you leave in the morning and come back home in the evening/night. If you are
  commuting at 12 AM, it will discord that day's data. This made my code very simple, like really
   simple, and I don't know whether anyone is affected by it. I can fix it if it's really needed.
    let me know! 
2. It can't work if you are logged in to google with two devices and they are in different places
. Google is recording your location in both places and there is no way to clean up such history
 file.

### Why it's not an app/website?

Because it deals with your location history. You should NEVER EVER share your location history
 because it is an essential part of your privacy. This program runs locally, so nothing is
  shared with outside world.