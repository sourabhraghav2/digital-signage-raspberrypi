# digital-signage-raspberrypi

Steps to start app:
1). change properties filefor driver depending upon the machine/chrome/driver version.
2). run mvnw package
3). extract the jar from targate and rename to <jarname.jar>
4). run : java -jar <jarname.jar>
5). check ../log/DigitalSinage.log for logs.


Output: will display chrome screen on full screen mode with some URL loaded.


Rest Api Operations:
1).UpdateUrl:
Method:POST
uri: <localhost/ip>:3447/receiveRequest
Data:{
    "url":"https://www.google.com",
    "requestType":"UpdateUrlRequest",
    "id":1,
    "description":"Updates the URL on screen"
}
Type: content-type: application/json
Expected response : true/ false

2).StopBrowser:
Method : POST
uri: <localhost/ip>:3447/receiveRequest
Data: {
    "requestType":"StopBrowserRequest",
    "id":1,
    "description":"Stop screen"
}
Type: content-type: application/json
Expected response : true/ false



Enjoy Learning !!!!!!!!

