package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.domain.PersonalChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalChatRepository extends JpaRepository<PersonalChat, Long>, PersonalChatRepositoryCustom {

}
