package albaAyo.albaayo.config.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    boolean existsById(String id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from RefreshToken r where r.id = :id")
    void refreshTokenDelete(@Param("id") String id);
}
