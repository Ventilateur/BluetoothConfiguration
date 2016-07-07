# BluetoothConfiguration
Configure a module bluetooth by sending AT commands through serial port, an "USB to TTL" module or a microcontroller will be needed </br>
User can either enter the AT commands manually or import them from a text file </br>
For those who use text file, there are two other modes apart from sending commands: </br>
* *@DELAY x :* will pause the program for x milliseconds </br>
* *@@Foo foo :* line will be treated as a comment, which means it will not be sent </br>
</br>

Two examples about how to use a text file configuration are included (cmdHC06.txt and cmdSeeed.txt) as well as the output file (outputSeeed.txt) </br>
</br>
About executable jar file: use one of these commands:
* *java -jar ConfigBlt.jar*
* *java -jar ConfigBlt.jar COMx* 
* *java -jar ConfigBlt.jar COMx commandFile.txt*
* *java -jar ConfigBlt.jar COMx commandFile.txt > outputFile.txt*
  
