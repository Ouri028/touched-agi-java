package touched.agi.java.app.methods;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;

import javax.annotation.Nullable;

public abstract class Methods {
    protected AgiRequest request;
    protected AgiChannel channel;

    public Methods(AgiRequest req, AgiChannel chl){
        request = req;
        channel = chl;
    }

    /**
     *  response.result = "-1" | "0"
     *
     * -1. channel failure
     *
     *  0 successful
     * https://wiki.asterisk.org/wiki/display/AST/Asterisk+17+AGICommand_answer
     */

    public void Answer() throws AgiException {
        channel.answer();
    }

    /**
     * @return
     * 0 Channel is down and available
     * 1 Channel is down, but reserved
     * 2 Channel is off hook
     * 3 Digits (or equivalent) have been dialed
     * 4 Line is ringing
     * 5 Remote end is ringing
     * 6 Line is up
     * 7 Line is busy
     * @throws AgiException
     */
    public int ChannelStatus() throws AgiException {
        return channel.getChannelStatus();
    }

    /**
     * Plays the given file allowing the user to control the streaming by using forwardDigit for forward, rewindDigit for rewind and pauseDigit for pause. Pressing one of the escape digits stops streaming. The file is played starting at the indicated offset, use 0 to start at the beginning.
     *
     * Params:
     * @param file – the name of the file to stream, must not include extension.
     * @param escapeDigits – contains the digits that allow the user to interrupt this command. May be null if you don't want the user to interrupt.
     * @param offset – the offset samples to skip before streaming, use 0 to start at the beginning.
     * @param forwardDigit – the digit for fast forward.
     * @param rewindDigit – the digit for rewind.
     * @param pauseDigit – the digit for pause and unpause.
     * @return - The DTMF digit pressed or 0x0 if none was pressed
     * @throws AgiException
     */
    public void ControlStreamFile(String file, @Nullable String escapeDigits, @Nullable int offset, @Nullable String forwardDigit, @Nullable String rewindDigit, @Nullable String pauseDigit) throws AgiException {
        channel.controlStreamFile(file, escapeDigits, offset, forwardDigit, rewindDigit, pauseDigit);
    }

    public String GetData(String file, long timeout) throws AgiException {
        return channel.getData(file, timeout);
    }

}
