
# Dat153Oblig1
### All Tests OK
![403-Screenshot-03-14_13h-27m-33s](https://user-images.githubusercontent.com/54099045/158171983-6c174721-e23b-4ea2-8e0a-a181eca36796.png)

![402-Screenshot-03-14_09h-58m-16s](https://user-images.githubusercontent.com/54099045/158138494-615e36c2-a537-4f4c-80b3-8c146294b6ad.png)
### Build fails due to one test not working when running the full gradle connectedDebugAndroidTest. It works seperatly. 


Authors:

jwlei

finleif

pofurre


## Generated APKs during testing

Test apk

App apk


## What ADB commands

adb install -r myapp.apk

adb install -r mytestapp.apk

adb shell am instrument -w -e debug false -e class <packageName>.<className> <packageName>.test/testRunner
