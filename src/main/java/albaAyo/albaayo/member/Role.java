package albaAyo.albaayo.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    EMPLOYER("ROLE_EMPLOYER", "고용주"),
    WORKER("ROLE_WORKER", "근로자"),
    GUEST("ROLE_GUEST", "손님");

    private final String key;
    private final String title;
}
