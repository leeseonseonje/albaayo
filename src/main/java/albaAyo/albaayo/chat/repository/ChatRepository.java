package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.Chat;
import albaAyo.albaayo.company.repository.CompanyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long>, ChatRepositoryCustom {

}
