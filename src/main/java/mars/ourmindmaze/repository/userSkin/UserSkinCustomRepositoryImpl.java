package mars.ourmindmaze.repository.userSkin;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.domain.QUserSkin;
import mars.ourmindmaze.domain.UserSkin;
import mars.ourmindmaze.vo.UserSkinVO;

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
                                Expressions.asDateTime(u.createdDate),
                                u.skin.character.id,
                                u.skin.character.name
                        )
                ).from(u)
                .where(u.user.id.eq(id))
                .orderBy(u.createdDate.desc())
                .leftJoin(u.user)
                .leftJoin(u.skin)
                .leftJoin(u.skin.character)
                .fetch();

        return list;
    }
}
