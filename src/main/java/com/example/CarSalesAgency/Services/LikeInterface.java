package com.example.CarSalesAgency.Services;

public interface LikeInterface {

    String toggleLike(String userId, Long commentId);

    long getLikesCount(Long commentId);
}
