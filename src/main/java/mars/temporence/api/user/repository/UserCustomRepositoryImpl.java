package mars.temporence.api.user.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.temporence.api.user.domain.QUser;
import mars.temporence.api.user.event.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserVO> findAllUser(Pageable pageable) {
        QUser u = QUser.user;
        QueryResults<UserVO> list = queryFactory.select(
                        Projections.constructor(
                                UserVO.class,
                                u.id,
                                u.username,
                                Expressions.asDateTime(u.createdDate)
                        )).from(u)
                .orderBy(u.createdDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        return new PageImpl(list.getResults(), pageable, list.getTotal());
    }
}
