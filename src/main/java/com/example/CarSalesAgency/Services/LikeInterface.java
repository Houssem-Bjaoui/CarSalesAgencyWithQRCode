package com.example.CarSalesAgency.Services;

public interface LikeInterface {

    String toggleLike(Long userId, Long commentId);

    long getLikesCount(Long commentId);
}
