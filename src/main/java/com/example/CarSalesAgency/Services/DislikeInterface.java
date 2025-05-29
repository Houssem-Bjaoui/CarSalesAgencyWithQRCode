package com.example.CarSalesAgency.Services;

public interface DislikeInterface {

    String toggleDislike(String userId, Long commentId);
    long getDislikesCount(Long commentId);

}
