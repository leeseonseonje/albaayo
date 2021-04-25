package albaAyo.albaayo.chat;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Chat extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String name;

    private String chatHistory;

}
