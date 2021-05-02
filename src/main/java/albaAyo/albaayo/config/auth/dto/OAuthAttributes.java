package albaAyo.albaayo.config.auth.dto;

import albaAyo.albaayo.member.Member;
import albaAyo.albaayo.member.Role;
import albaAyo.albaayo.member.employer.Employer;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String birth;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String picture, String birth) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.birth = birth;
    }

    public static OAuthAttributes of(String registrationId, String memberNameAttributeName,
                                     Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(memberNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String memberNameAttributeName,
                                            Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(memberNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String memberNameAttributeName, Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .birth(response.get("birthyear") + "-" + response.get("birthday"))
                .attributes(response)
                .nameAttributeKey(memberNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return new Employer(email, name, picture, birth, Role.EMPLOYER);
    }
}
