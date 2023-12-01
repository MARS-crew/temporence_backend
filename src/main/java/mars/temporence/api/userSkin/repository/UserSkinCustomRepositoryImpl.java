package mars.temporence.api.userSkin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.temporence.api.userSkin.domain.QUserSkin;
import mars.temporence.api.userSkin.event.vo.UserSkinVO;

import java.util.List;

@RequiredArgsConstructor
public class UserSkinCustomRepositoryImpl implements UserSkinCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserSkinVO> findUserSkinList(Long id) {
        QUserSkin u = QUserSkin.userSkin;
        List<UserSkinVO> list = queryFactory.select(
                        Projections.constructor(
                                UserSkinVO.class,
                                u.id,
                                u.skin.id,
                                u.user.id,
                                u.skin.name,
                                Expressions.asDateTime(u.createdDate)
                        )
                ).from(u)
                .where(u.user.id.eq(id))
                .orderBy(u.createdDate.desc())
                .leftJoin(u.user)
                .leftJoin(u.skin)
                .fetch();

        return list;
    }
}
