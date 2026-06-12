package com.mycompany.tubes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KerusakanTest {

    @Test
    void addLaporKerusakanShouldAddDamageReport() {
        ArrayList<Kerusakan> kerusakanList = new ArrayList<>();
        Kerusakan kerusakan = new Kerusakan(1, 1, 1, "Tiang patah", "foto.jpg", "Tenda", 20_000, "Belum Dibayar");

        kerusakan.addLaporKerusakan(kerusakanList);

        assertEquals(1, kerusakanList.size());
        assertEquals("Tiang patah", kerusakanList.get(0).getDeskripsi());
    }

    @Test
    void confirmPembayaranShouldSetStatusPaidAndIncreaseDeposit() {
        Kerusakan kerusakan = new Kerusakan(1, 1, 1, "Tiang patah", "foto.jpg", "Tenda", 20_000, "Belum Dibayar");
        ArrayList<Deposit> deposits = new ArrayList<>();
        deposits.add(new Deposit(1, 1, 100_000));

        kerusakan.confirmPembayaran(deposits);

        assertEquals("Dibayar", kerusakan.getStatusPembayaran());
        assertEquals(120_000, deposits.get(0).getSaldo());
    }
}
