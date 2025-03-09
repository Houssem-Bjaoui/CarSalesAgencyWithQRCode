package com.example.CarSalesAgency.Services;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeInterface {

    // Générer un QR code sous forme de chaîne Base64
    String generateQRCode(String text) throws WriterException, IOException;

    // Générer un QR code et le sauvegarder dans un fichier
    void generateQRCodeToFile(String text, String filePath) throws WriterException, IOException;  // Déclarer l'exception ici
}
