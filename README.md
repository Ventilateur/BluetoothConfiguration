# BluetoothConfiguration
Configure a module bluetooth by sending AT commands through serial port, an "USB to TTL" module or a microcontroller will be needed </br>
User can either enter the AT commands manually or import them from a text file </br>
For those who use text file, there are two other modes apart from sending commands: </br>
...@DELAY x : will pause the program for x milliseconds </br>
...@@Foo foo : line will be treated as a comment, which means it will not be sent </br>
  
