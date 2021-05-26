package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.QCompany;
import albaAyo.albaayo.company.dto.CompanyDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.company.domain.QCompany.*;


public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CompanyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<CompanyDto> findCompanies(Long id) {

        return queryFactory
                .select(Projections.constructor(CompanyDto.class,
                        company.id, company.name, company.location))
                .from(company)
                .where(company.member.id.eq(id))
                .orderBy(company.name.desc())
                .fetch();
    }
}
