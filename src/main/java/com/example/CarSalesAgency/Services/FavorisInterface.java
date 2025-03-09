package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Entities.Favoris;

import java.util.List;

public interface FavorisInterface {

    Favoris addToFavoris(Long userId, Long vehiculeId);

    void removeFromFavoris(Long userId, Long vehiculeId);

    List<Favoris> getFavorisByUser(Long userId);
}
