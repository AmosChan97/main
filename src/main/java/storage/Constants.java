package storage;

/**
 * Contains project-wide constants.
 */
public class Constants {
    public static final String ROOMFILENAME = "data\\roomlist.txt";
    public static final String INVENTORYFILENAME = "data\\inventory.txt";
    public static final String USERFILENAME = "data\\users.txt";
    public static final String DIRECTORY = "data";
    public static final int ROOMCODE = 0;
    public static final int TIME = 3;
    public static final int TIMESTART = 3;
    public static final int TIMEEND = 4;
    public static final int ITEMRMCODE = 0;
    public static final int ITEMNAME = 1;
    public static final int ITEMQTY = 2;
    public static final int ROOMDATETIMESTART = 1;
    public static final int ROOMDATETIMEEND = 2;
    public static final String INVALIDDATETIME = "Please enter a valid date or time.";
    public static final String ADDROOMFORMAT = "Please enter the following to add a room:\n"
            + "addroom <ROOMCODE> <CAPACITY>.\n";
    public static final String WRONGDATETIMEFORMAT = "Please enter valid date and start-time for the room.";
    public static final String NOSTARTENDTIME = "Please enter a start/end time for the room.";
    public static final String UNHAPPY = ":-(";
    public static final int CAPACITY = 1;
    public static final String INVALIDCAPACITY = "Capacity cannot be negative";
}
