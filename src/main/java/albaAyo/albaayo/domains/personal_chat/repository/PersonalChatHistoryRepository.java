package albaAyo.albaayo.domains.personal_chat.repository;

import albaAyo.albaayo.domains.personal_chat.domain.PersonalChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalChatHistoryRepository extends JpaRepository<PersonalChatHistory, Long> {
}
