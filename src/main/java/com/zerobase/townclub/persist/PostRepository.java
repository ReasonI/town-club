package com.zerobase.townclub.persist;

import com.zerobase.townclub.persist.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  @Query(value = "SELECT *, "
      + "ST_Distance_Sphere( point(p.lon, p.lat), point( :userLon, :userLat) ) AS dist"
      + "FROM POST AS p"
      + "WHERE (ST_Distance_Sphere(point(p.lon, p.lat), point( :userLon, :userLat))) <= :userDistance"
      + "ORDER BY dist", nativeQuery = true)
  List<Post> findPostByDistance(@Param("userLon") double userLon, @Param("userLat") double userLat,
      @Param("userDistance") int userDistance);
}
