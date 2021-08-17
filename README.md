# ***Touched-Agi-Java***

### Description:
Touched-Agi-Java is an attempt to bring over the ease of use of touched-agi, but instead of JavaScipt/TypeScript you can write the AGIs in Java.


### Example:

FastAGIServer.java
```java
import touched.agi.java.server.Server;

public class Main {
    public static void main(String[] args) {
        /**
         * Create an instance of Server.
         */
        Server server;
        /**
         * Instantiate Server and assign it a port. 
         */
        server = new Server(5000);
        /**
         * Start the server.
         */
        server.Startup();
    }
}
```

Agi.java

```java
import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;
import touched.agi.java.methods.Methods;

public class Agi extends BaseAgiScript {
    
@Override
public void service(AgiRequest agiRequest, AgiChannel agiChannel) throws AgiException {
Methods ctx = new Methods(agiRequest, agiChannel) {
        
        @Override
        public String GetData(String file, long timeout) throws AgiException {
        return super.GetData(file, timeout);
        }

        @Override
        public void SayDigits(String digits) throws AgiException {
            super.SayDigits(digits);
        }
    };
        String data = ctx.GetData("beep", 14000);
        ctx.SayDigits(data);
    }


}
```

### Properties:
In order to send requests to the Agi classes we need to tell the FastAgi which classes to send the Agi Requests to.
This can be specified in the fastagi-mapping.properties file.
```properties
sample.agi = Agi
```
With the settings above the Agi URL will look like this.
```
agi://localhost:5000/sample.agi
```


### Running:

Once you've created your Agi script and compiled it to a .jar file, it is now ready to be run on the server.

In order to run the Agi you can use the following command.

```bash
java -jar sample_agi.jar
```


### Recommendations:

If you would like to have the FastAgi script run in the background will a managed system, I would recommend using PM2 to manage the FastAgi Server.

### Author:
Sylvester Stephenson

sylvesterstephenson028@gmail.com

### Credits:
Credits go to asterisk-java for the asterisk-java.jar file.

https://github.com/asterisk-java/asterisk-java.git