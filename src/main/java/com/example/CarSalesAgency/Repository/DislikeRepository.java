package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DislikeRepository extends JpaRepository<Dislike, Long> {
    boolean existsByUser_IdAndComment_Id(String userId, Long commentId);
    void deleteByUser_IdAndComment_Id(String userId, Long commentId);
    long countByComment_Id(Long commentId);
}
