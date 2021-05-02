package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
