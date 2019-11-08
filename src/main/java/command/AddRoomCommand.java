
package command;

import booking.ApprovedList;
import inventory.Inventory;

import booking.BookingList;
import exception.DukeException;
import storage.Storage;
import room.Room;
import room.RoomList;
import storage.StorageManager;
import ui.Ui;
import user.UserList;

import java.io.IOException;

public class AddRoomCommand extends Command {

    private String[] splitC;
    private String roomcode;
    private String[] datesplit;
    private String dateStartTime;
    private String endTime;

    //@@author zkchang97
    /**
     * Creates a new room entry in the list of rooms.
     * Format is addroom ROOMCODE /date DD/MM/YYYY HHMM /to HHMM
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException when format is incorrect
     */
    public AddRoomCommand(String input, String[] splitStr) throws DukeException {
        if (splitStr.length == 1) {
            throw new DukeException("Please enter the following to add a room:\n"
                    + "addroom ROOMCODE /date DD/MM/YYYY HHMM /to HHMM.\n");
        }
        if (!input.contains(" /date ")) {
            throw new DukeException("Please enter correct date and start-time for the room.");
        }
        if (!input.contains(" /to ")) {
            throw new DukeException("Please enter an end-time for the room.");
        }
        // addroom ROOMCODE /date DATE TIMESTART /to TIMEEND
        String tempAR = input.substring(8);
        splitC = tempAR.split(" /date ", 2); // splitC[] = {ROOMCODE, DATE TIMESTART /to TIMEEND}
        this.roomcode = splitC[0]; // ROOMCODE
        this.datesplit = splitC[1].split(" /to ", 2); // datesplit[] == {DATE TIMESTART, TIMEEND}
        this.dateStartTime = datesplit[0];
        this.endTime = datesplit[1];
    }

    /**
     * Executes the command to add a room to the system.
     * @param roomList room list
     * @param bookingList bookings list
     * @param ui user interface
     * @param allStorage booking storage in command execution
     * @throws IOException if input entry is incorrect
     */
    @Override

    public void execute(UserList userList, Inventory inventory, RoomList roomList,
                        BookingList bookingList, ApprovedList approvedList, Ui ui,
                        StorageManager allStorage)
            throws IOException {
        Room addroom = new Room(roomcode, dateStartTime, endTime);
        roomList.add(addroom);
        allStorage.getRoomStorage().saveToFile(roomList);
        ui.addToOutput("Got it, I've added this room.\n"
            + addroom.toString() + "\n" + "Now you have " + roomList.size() + " room(s) in the list.");
    }
}
