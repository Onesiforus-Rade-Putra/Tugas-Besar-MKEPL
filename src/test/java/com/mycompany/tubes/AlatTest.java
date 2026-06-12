package com.mycompany.tubes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AlatTest {

    @Test
    void addAlatShouldAddEquipmentToList() {
        ArrayList<Alat> alatList = new ArrayList<>();
        Alat alat = new Alat("Tenda", 5, "Tersedia");

        Alat.addAlat(alatList, alat);

        assertEquals(1, alatList.size());
        assertEquals("Tenda", alatList.get(0).getNama());
    }

    @Test
    void updateAlatShouldChangeJumlahAndStatus() {
        ArrayList<Alat> alatList = new ArrayList<>();
        alatList.add(new Alat("Tenda", 5, "Tersedia"));

        Alat.updateAlat(alatList, "Tenda", 0, "Tidak Tersedia");

        assertEquals(0, alatList.get(0).getJumlah());
        assertEquals("Tidak Tersedia", alatList.get(0).getStatus());
    }

    @Test
    void deleteAlatShouldRemoveEquipmentByName() {
        ArrayList<Alat> alatList = new ArrayList<>();
        alatList.add(new Alat("Tenda", 5, "Tersedia"));

        Alat.deleteAlat(alatList, "Tenda");

        assertTrue(alatList.isEmpty());
    }
}
