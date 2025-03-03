class TicketBooking {
    private int totalSeats = 10; 
    private final Object lock = new Object();  

    public void bookSeat(String customerName, boolean isVIP) {
        synchronized (lock) {  
            if (totalSeats > 0) {
                totalSeats--;
                System.out.println(customerName + " successfully booked a seat! Remaining seats: " + totalSeats);
            } else {
                System.out.println("Sorry, no seats available for " + customerName);
            }
        }
    }

    public void bookTicket(Thread thread, String customerName, boolean isVIP) {
        thread.start();
        try {
            thread.join();  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BookingThread extends Thread {
    private TicketBooking ticketBooking;
    private String customerName;
    private boolean isVIP;

    public BookingThread(TicketBooking ticketBooking, String customerName, boolean isVIP) {
        this.ticketBooking = ticketBooking;
        this.customerName = customerName;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        ticketBooking.bookSeat(customerName, isVIP);
    }
}

public class TicketBookingSystem {

    public static void main(String[] args) {
        TicketBooking ticketBooking = new TicketBooking();

        BookingThread vipCustomer1 = new BookingThread(ticketBooking, "VIP Customer 1", true);
        BookingThread regularCustomer1 = new BookingThread(ticketBooking, "Regular Customer 1", false);
        BookingThread vipCustomer2 = new BookingThread(ticketBooking, "VIP Customer 2", true);
        BookingThread regularCustomer2 = new BookingThread(ticketBooking, "Regular Customer 2", false);

        vipCustomer1.setPriority(Thread.MAX_PRIORITY);
        regularCustomer1.setPriority(Thread.NORM_PRIORITY);
        vipCustomer2.setPriority(Thread.MAX_PRIORITY);
        regularCustomer2.setPriority(Thread.NORM_PRIORITY);

        ticketBooking.bookTicket(vipCustomer1, "VIP Customer 1", true);
        ticketBooking.bookTicket(regularCustomer1, "Regular Customer 1", false);
        ticketBooking.bookTicket(vipCustomer2, "VIP Customer 2", true);
        ticketBooking.bookTicket(regularCustomer2, "Regular Customer 2", false);
    }
}
