package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUser_IdAndComment_Id(String userId, Long commentId);
    void deleteByUser_IdAndComment_Id(String userId, Long commentId);
    long countByComment_Id(Long commentId);
}