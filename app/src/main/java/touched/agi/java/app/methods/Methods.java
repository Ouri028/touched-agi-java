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
     * @throws AgiException
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
    public char ControlStreamFile(String file, @Nullable String escapeDigits, @Nullable Integer offset, @Nullable String forwardDigit, @Nullable String rewindDigit, @Nullable String pauseDigit) throws AgiException {
        return channel.controlStreamFile(file, escapeDigits, offset, forwardDigit, rewindDigit, pauseDigit);
    }

    /**
     * Deletes a family or specific keytree within a family in the Asterisk database.
     * response.result = "0" | "1"
     *
     * 0   if successful
     *
     * 1  otherwise.
     *
     * https://wiki.asterisk.org/wiki/display/AST/Asterisk+17+AGICommand_database+deltree
     * Params:
     * @param family – the family of the entry to delete.
     * @param key – the key of the entry to delete.
     * @throws AgiException
     */
    public void DatabaseDel(String family, String key) throws AgiException{
        channel.databaseDel(family, key);
    }

    /**
     *
     * @param family
     * @param keyTree
     * @throws AgiException
     */
    public void DatabaseDelTree(String family, String keyTree) throws AgiException{
        channel.databaseDelTree(family, keyTree);
    }

    /**
     Retrieves an entry in the Asterisk database for a given family and key.

     Params:
     * @param family – the family of the entry to retrieve.
     * @param key - the key of the entry to retrieve.
     * @return - the value of the given family and key or null if there is no such value.
     * @throws AgiException
     */
    public String DatabaseGet(String family, String key) throws AgiException{
        return channel.databaseGet(family, key);
    }

    /**
     Adds or updates an entry in the Asterisk database for a given family, key, and value.

     Params:
     * @param family - the family of the entry to add or update.
     * @param key – the key of the entry to add or update.
     * @param value – the new value of the entry
     * @throws AgiException
     */

    public void DatabasePut(String family, String key, String value) throws AgiException{
        channel.databasePut(family, key, value);
    }

    /**
     * Executes the given command.
     Params:
     * @param command – the name of the command to execute, for example "Dial".
     * @param options – the parameters to pass to the application, for example "SIP/123".
     * @return - the return code of the application of -2 if the application was not found.
     * @throws AgiException
     */
    public int Exec(String command, @Nullable String... options) throws AgiException {
        return channel.exec(command, options);
    }

    /**
     Plays the given file and waits for the user to enter DTMF digits until he presses '#' or the timeout occurs. The user may interrupt the streaming by starting to enter digits.

     Params:
     * @param file – the name of the file to play
     * @param timeout – the timeout in milliseconds to wait for user input. 0 means standard timeout value, -1 means "ludicrous time" (essentially never times out).
     * @return - a String containing the DTMF the user entered
     * @throws AgiException
     */
    public String GetData(String file, long timeout) throws AgiException {
        return channel.getData(file, timeout);
    }

    /**
     Evaluates a channel expression for the given channel.To extract the caller id of channel use getFullVariable("${CALLERID(name)}", "SIP/john-0085d860");.
     Available since Asterisk 1.2.

     Params:
     * @param expr – the the expression to evaluate.
     * @param channelName – the name of the channel.
     * @return - the value of the given expression or null if not set.
     * @throws AgiException
     */
    public String GetFullVariable(String expr, String channelName) throws AgiException {
        return channel.getFullVariable(expr, channelName);
    }

    /**
     Plays the given file, and waits for the user to press one of the given digits. If none of the escape digits is pressed while streaming the file it waits for the specified timeout still waiting for the user to press a digit.

     Params:
     * @param file – the name of the file to stream, must not include extension.
     * @param escapeDigits – contains the digits that the user is expected to press.
     * @param timeout – the timeout in milliseconds to wait if none of the defined esacpe digits was presses while streaming.
     * @return - the DTMF digit pressed or 0x0 if none was pressed
     * @throws AgiException
     */
    public char GetOption(String file, @Nullable String escapeDigits, @Nullable long timeout) throws AgiException {
        return channel.getOption(file, escapeDigits, timeout);
    }

}
