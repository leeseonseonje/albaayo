package albaAyo.albaayo.personal_chat.repository;

import albaAyo.albaayo.personal_chat.domain.PersonalChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalChatHistoryRepository extends JpaRepository<PersonalChatHistory, Long> {
}
