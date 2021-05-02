package albaAyo.albaayo.personalchat.repository;

import albaAyo.albaayo.personalchat.domain.PersonalChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalChatHistoryRepository extends JpaRepository<PersonalChatHistory, Long> {
}
