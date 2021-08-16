package touched.agi.java.app.methods;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.command.AgiCommand;
import org.asteriskjava.fastagi.reply.AgiReply;

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
     invoke the dial command

     Params:
     * @param target
     * @param timeout
     * @param options
     * @throws AgiException
     */
    public void Dial(String target, int timeout, String options) throws AgiException {
        channel.dial(target, timeout, options);
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

    /**
     Returns the value of the current channel or global variable. Supports functions and builtin variables. To retrieve the caller id you can use getVariable("CALLERID(name)"); Does not support expression parsing, use getFullVariable(String) in those cases.

     Params:
     * @param name – the name of the variable (or function call) to retrieve.
     * @return - the value of the given variable or null if not set.
     * @throws AgiException
     */

    public String GetVariable(String name) throws AgiException {
        return channel.getVariable(name);
    }

    /**
     Calls a subroutine in the dialplan
     This method is available since Asterisk 1.6.

     Params:
     * @param context – the context of the called subroutine.
     * @param extension – the extension in the called context.
     * @param priority – the priority of the called extension.
     * @param arguments – optional arguments to be passed to the subroutine. They should be separated by comma. They will accessible in the form of ${ARG1}, ${ARG2}, etc in the subroutine body.
     */
    public void GoSub(String context, String extension, String priority, String... arguments) throws AgiException {
        channel.gosub(context, extension, priority, arguments);
    }

    /**
     Hangs the channel up.

     * @throws AgiException
     */

    public void HangUp() throws AgiException {
        channel.hangup();
    }

    /**
     Record to a file until a given dtmf digit in the sequence is received.

     Params:
     * @param file – the name of the file to stream, must not include extension.
     * @param format – the format of the file to be recorded, for example "wav".
     * @param escapeDigits – contains the digits that allow the user to end recording.
     * @param timeout – the maximum record time in milliseconds, or -1 for no timeout.
     * @param offset – the offset samples to skip.
     * @param beep – true if a beep should be played before recording.
     * @param maxSilence – The amount of silence (in seconds) to allow before returning despite the lack of dtmf digits or reaching timeout.
     * @return - the DTMF digit pressed or 0x0 if none was pressed.
     */

    public char Record(String file, String format, String escapeDigits, int timeout, int offset, boolean beep, int maxSilence) throws AgiException {
        return channel.recordFile(file, format, escapeDigits, timeout, offset, beep, maxSilence);
    }

    /**
     Sends a command to asterisk and returns the corresponding reply. The reply is also available through getLastReply().

     * @param command – the command to send.
     * @return - the reply of the asterisk server containing the return value.
     * @throws AgiException - org.asteriskjava.fastagi.AgiException – if the command can't be sent to Asterisk (for example because the channel has been hung up)
     */
    public AgiReply SendCommand(AgiCommand command) throws AgiException {
        return channel.sendCommand(command);
    }

    /**
     Says the given character string, returning early if any of the given DTMF number are received on the channel.

     Params:
     * @param text – the text to say.
     * @param escapeDigits – a String containing the DTMF digits that allow the user to escape.
     * @throws AgiException - the DTMF digit pressed or 0x0 if none was pressed.
     */

    public char SayAlpha(String text, String escapeDigits) throws AgiException {
        return channel.sayAlpha(text, escapeDigits);
    }

    /**
     Says the given time.
     Available since Asterisk 1.2.

     Params:
     * @param time – the time to say in seconds elapsed since 00:00:00 on January 1, 1970, Coordinated Universal Time (UTC)
     * @throws AgiException
     */
    public void SayDateTime(long time) throws AgiException {
        channel.sayDateTime(time);
    }

    /**
     Says the given digit string.

     Params:
     * @param digits – the digit string to say
     * @throws AgiException
     */
    public void SayDigits(String digits) throws AgiException {
        channel.sayDigits(digits);
    }

    /**
     Says the given number.

     Params:
     * @param number – the number to say.
     * @throws AgiException
     */
    public void SayNumber(String number) throws AgiException {
        channel.sayNumber(number);
    }

    /**
     Says the given character string with phonetics.

     Params:
     * @param text – the text to say.
     * @throws AgiException
     */
    public void SayPhonetic(String text) throws AgiException {
        channel.sayPhonetic(text);
    }

    /**
     Says the given time.

     Params:
     * @param time – the time to say in seconds since 00:00:00 on January 1, 1970.
     * @throws AgiException
     */
    public void SayTime(Long time) throws AgiException {
        channel.sayTime(time);
    }

    /**
     Cause the channel to automatically hangup at the given number of seconds in the future.

     Params:
     0 disables the autohangup feature.

     * @param time – the number of seconds before this channel is automatically hung up.
     * @throws AgiException
     */
    public void SetAutoHangup(int time) throws AgiException {
        channel.setAutoHangup(time);
    }

    /**
     Sets the caller id on the current channel.

     Params:
     * @param callerid – the raw caller id to set, for example "John Doe<1234>".
     * @throws AgiException
     */
    public void SetCallerID(String callerid) throws AgiException {
        channel.setCallerId(callerid);
    }

    /**
     Sets the context for continuation upon exiting the application.

     Params:
     * @param context – the context for continuation upon exiting the application
     * @throws AgiException
     */
    public void SetContext(String context) throws AgiException {
        channel.setContext(context);
    }

    /**
     Sets the extension for continuation upon exiting the application.

     Params:
     * @param extension – the extension for continuation upon exiting the application.
     * @throws AgiException
     */
    public void SetExtension(String extension) throws AgiException {
        channel.setExtension(extension);
    }


    /**
     Sets the priority or label for continuation upon exiting the application.

     Params:
     * @param priority – the priority or label for continuation upon exiting the application
     * @throws AgiException
     */
    public void SetPriority(String priority) throws AgiException {
        channel.setPriority(priority);
    }

    /**
     Sets the value of the current channel or global variable to a new value. Supports functions and builtin variables. To set the caller id you can use setVariable("CALLERID(name)", "John Doe");

     Params:
     * @param name – the name of the variable (or function call) to set.
     * @param value – the new value to set.
     * @throws AgiException
     */
    public void SetVariable(String name, String value) throws AgiException {
        channel.setVariable(name, value);
    }

    /**

     Plays the given file.

     Params:
     * @param file – name of the file to play
     * @throws AgiException
     */
    public void StreamFile(String file) throws AgiException {
        channel.streamFile(file);
    }

    /**
     Sends a message to the Asterisk console via the verbose message system.

     Params:
     * @param message – the message to send.
     * @param level – the verbosity level to use. Must be in [1..4].
     * @throws AgiException
     */
    public void Verbose(String message, int level) throws AgiException {
        channel.verbose(message, level);
    }

    /**
     Waits up to 'timeout' milliseconds to receive a DTMF digit.

     Params:
     * @param timeout – timeout the milliseconds to wait for the channel to receive a DTMF digit, -1 will wait forever.
     * @return - the DTMF digit pressed or 0x0 if none was pressed.
     * @throws AgiException
     */
    public char WaitForDigits(int timeout) throws AgiException {
        return channel.waitForDigit(timeout);
    }

}
