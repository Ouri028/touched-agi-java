package touched.agi.java.app.methods;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;

public abstract class Methods {
    protected AgiRequest request;
    protected AgiChannel channel;

    public Methods(AgiRequest req, AgiChannel chl){
        request = req;
        channel = chl;
    }
    public String GetData(String file, long timeout) throws AgiException {
        return channel.getData(file, timeout);
    }
}
