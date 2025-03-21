package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Entities.Favoris;

import java.util.List;

public interface FavorisInterface {

    Favoris addToFavoris(String userId, Long vehiculeId);

    void removeFromFavoris(String userId, Long vehiculeId);

    List<Favoris> getFavorisByUser(String userId);
}
