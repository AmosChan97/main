package control;

import command.Command;
import exception.DukeException;
import room.RoomList;
import storage.BookingConstants;
import storage.Constants;
import storage.Storage;
import ui.Ui;
import booking.BookingList;
import user.Guest;
import user.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Main control.Duke class
 * control.Duke is a chatbot that manage tasks for the user
 */
public class Duke {
    private Storage bookingStorage;
    private BookingList bookingList;
    private RoomList roomList;
    private Ui ui;
    private User user;
    private boolean isExit;
    private Storage roomStorage;


    /**
     * Constructor for control.Duke
     * @param bookingListFile path of text file containing bookings list
     */
    public Duke(String bookingListFile, String roomListFile) {
        ui = new Ui();
        ui.showWelcome();
        user = new Guest("guest");
        bookingStorage = new Storage(bookingListFile);
        roomStorage = new Storage(roomListFile);
        try {
            bookingList = new BookingList(bookingStorage.load());
            roomList = new RoomList(roomStorage.load());
        } catch (FileNotFoundException | DukeException e) {
            ui.showLoadingError();
            bookingList = new BookingList();
            roomList = new RoomList();
        }
    }

    public String getResponse(String input) {
        try {
            ui.setOutput("");
            Command c = Parser.parse(input, user.getLoginStatus());
            c.execute(roomList, bookingList, ui, bookingStorage, roomStorage, user);
            System.out.println(ui.getOutput());
            return ui.getOutput();
        } catch (DukeException | IOException | ParseException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public BookingList getBookingList() {
        return bookingList;
    }
}