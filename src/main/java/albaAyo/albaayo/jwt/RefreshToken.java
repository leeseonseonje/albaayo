package albaAyo.albaayo.jwt;

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
    private String key;

    private String token;

    public RefreshToken updateValue(String token) {
        this.token = token;
        return this;
    }

    @Builder
    public RefreshToken(String key, String token) {
        this.key = key;
        this.token = token;
    }
}
