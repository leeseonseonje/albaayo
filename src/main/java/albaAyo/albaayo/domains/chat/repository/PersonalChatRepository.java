package albaAyo.albaayo.domains.chat.repository;

import albaAyo.albaayo.domains.chat.domain.PersonalChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalChatRepository extends JpaRepository<PersonalChat, Long>, PersonalChatRepositoryCustom {

}
