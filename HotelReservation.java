import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Room {
    int number; String category; boolean available = true;
    Room(int n, String c){ number=n; category=c; }
}

public class HotelReservation extends JFrame {
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<String> bookings = new ArrayList<>();

    HotelReservation(){
        rooms.add(new Room(101,"Deluxe"));
        rooms.add(new Room(102,"Deluxe"));
        rooms.add(new Room(201,"Standard"));
        rooms.add(new Room(202,"Suite"));

        setTitle("CodeAlpha Hotel Reservation");
        setSize(500,400);
        setLayout(new GridLayout(5,1,10,10));

        JButton search = new JButton("Search Rooms");
        JButton book = new JButton("Book Room");
        JButton cancel = new JButton("Cancel Booking");
        JButton view = new JButton("View Bookings");
        JButton exit = new JButton("Exit");

        add(search); add(book); add(cancel); add(view); add(exit);

        search.addActionListener(e -> {
            String cat = JOptionPane.showInputDialog("Enter Category (Deluxe/Standard/Suite):");
            String res = "";
            for(Room r: rooms) if(r.available && r.category.equalsIgnoreCase(cat)) res+= "Room "+r.number+" - "+r.category+"\n";
            if(res.equals("")) res="No rooms available";
            JOptionPane.showMessageDialog(this,res);
        });

        book.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter Your Name:");
            String rno = JOptionPane.showInputDialog("Enter Room Number to Book:");
            int rn = Integer.parseInt(rno);
            for(Room r: rooms){
                if(r.number==rn && r.available){
                    r.available=false;
                    bookings.add(name+" - Room "+rn);
                    int pay = JOptionPane.showConfirmDialog(this,"Pay for Room "+rn+"?");
                    if(pay==JOptionPane.YES_OPTION) JOptionPane.showMessageDialog(this,"Booking Successful! Payment Done.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this,"Room not available");
        });

        cancel.addActionListener(e -> {
            String rno = JOptionPane.showInputDialog("Enter Room Number to Cancel:");
            int rn = Integer.parseInt(rno);
            for(Room r: rooms){
                if(r.number==rn && !r.available){
                    r.available=true;
                    JOptionPane.showMessageDialog(this,"Booking Cancelled for Room "+rn);
                    return;
                }
            }
        });

        view.addActionListener(e -> {
            String all = "Your Bookings:\n";
            for(String b: bookings) all+= b+"\n";
            JOptionPane.showMessageDialog(this,all);
        });

        exit.addActionListener(e -> System.exit(0));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new HotelReservation();
    }
}
