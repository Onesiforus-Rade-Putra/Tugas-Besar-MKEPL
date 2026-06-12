package com.mycompany.tubes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingTest {

    @Test
    void confirmBookingShouldSetStatusToPaidAndIncreaseDeposit() {
        Booking booking = new Booking(1, "rade", "rade@example.com", "123", "Bali", new Date(), 500_000, "Belum Dibayar", "Tenda");
        ArrayList<Deposit> deposits = new ArrayList<>();
        deposits.add(new Deposit(1, 0, 1_000_000));

        booking.confirmBooking(deposits);

        assertEquals("Dibayar", booking.getStatusPembayaran());
        assertEquals(1_500_000, deposits.get(0).getSaldo());
    }

    @Test
    void cancelBookingShouldNotChangePaidBookingStatus() {
        Booking booking = new Booking(1, "rade", "rade@example.com", "123", "Bali", new Date(), 500_000, "Dibayar", "Tenda");

        booking.cancelBooking();

        assertEquals("Dibayar", booking.getStatusPembayaran());
    }
}
