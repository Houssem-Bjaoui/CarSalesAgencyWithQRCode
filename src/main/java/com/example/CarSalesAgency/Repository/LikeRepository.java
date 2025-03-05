package com.example.CarSalesAgency.Repository;

import com.example.CarSalesAgency.Entities.Comment;
import com.example.CarSalesAgency.Entities.Like;
import com.example.CarSalesAgency.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUser_IdAndComment_Id(Long userId, Long commentId);
    void deleteByUser_IdAndComment_Id(Long userId, Long commentId);
    long countByComment_Id(Long commentId);
}