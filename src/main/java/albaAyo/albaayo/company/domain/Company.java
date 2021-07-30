package albaAyo.albaayo.company.domain;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.chat.domain.Chat;
import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.company.dto.RequestCompanyDto;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.notice.domain.Notice;
import albaAyo.albaayo.schedule.domain.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Member member;

    @Column(updatable = false, nullable = false, unique = true)
    private String businessRegistrationNumber;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String location;

    @Column(length = 1000)
    private String picture;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<JoinCompany> joinCompanies = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<Commute> commutes = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<Chat> chats = new ArrayList<>();

    @Builder
    public Company(String businessRegistrationNumber, String name, String location, String picture) {
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.name = name;
        this.location = location;
        this.picture = picture;
    }

    public void employerCreateCompany(Member member) {
        this.member = member;
    }

    public void updateCompany(RequestCompanyDto request) {
        this.name = request.getName();
        this.location = request.getLocation();
        this.picture = request.getPicture();
    }
}
