
# Dat153Oblig1
### All Tests OK
![403-Screenshot-03-14_13h-27m-33s](https://user-images.githubusercontent.com/54099045/158171983-6c174721-e23b-4ea2-8e0a-a181eca36796.png)

![368-WindowCaputure-TheNameQuizApp_–_CorrectScoreTest java_ TheNameQui-03-07_20h-44m-00s](https://user-images.githubusercontent.com/54099045/157106777-5c1eab69-6939-4b33-b68f-516666e556fd.png)

![369-WindowCaputure-TheNameQuizApp_–_ButtonToActivityTest java_ TheNam-03-07_20h-44m-27s](https://user-images.githubusercontent.com/54099045/157106810-56440de9-7022-47b5-a64b-37951a6cf546.png)

![397-WindowCaputure-TheNameQuizApp_–_QuizProgressTest java_ TheNameQui-03-13_22h-30m-18s](https://user-images.githubusercontent.com/54099045/158080025-298777ad-486d-44db-b7cb-87768b27494c.png)
![398-WindowCaputure-TheNameQuizApp_–_DatabaseRemoveTest java_ TheNameQ-03-13_22h-30m-35s](https://user-images.githubusercontent.com/54099045/158080027-46f47ce5-4ede-4212-8528-c833782bff39.png)
![399-WindowCaputure-TheNameQuizApp_–_DatabaseAddTest java_ TheNameQuiz-03-13_22h-30m-52s](https://user-images.githubusercontent.com/54099045/158080031-962d1236-58ca-4cd3-aec6-57ed08f5e9e6.png)

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
