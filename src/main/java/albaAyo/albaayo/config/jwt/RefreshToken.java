package albaAyo.albaayo.config.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    private String id;

    private String token;

    public void updateValue(String token) {
        this.token = token;
    }

    @Builder
    public RefreshToken(String id, String token) {
        this.id = id;
        this.token = token;
    }
}
