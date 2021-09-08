package albaAyo.albaayo.domains.personal_chat.repository;

import albaAyo.albaayo.domains.personal_chat.domain.PersonalChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalChatGroupRepository extends JpaRepository<PersonalChatGroup, Long> {
}
