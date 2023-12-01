package mars.temporence.api.skin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.temporence.api.skin.domain.QSkin;
import mars.temporence.global.enums.TeamType;
import mars.temporence.api.skin.event.vo.SkinVO;

import java.util.List;

@RequiredArgsConstructor
public class SkinCustomRepositoryImpl implements SkinCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SkinVO> findSkinList() {
        QSkin s = QSkin.skin;
        List<SkinVO> list = queryFactory.select(
                        Projections.constructor(
                                SkinVO.class,
                                s.id,
                                s.name,
                                Expressions.asDateTime(s.createdDate),
                                s.teamType
                        )
                ).from(s)
                .orderBy(s.createdDate.desc())
                .fetch();
        return list;
    }

    @Override
    public List<SkinVO> findSkinListByTeamType(TeamType teamType) {
        QSkin s = QSkin.skin;
        List<SkinVO> list = queryFactory.select(
                        Projections.constructor(
                                SkinVO.class,
                                s.id,
                                s.name,
                                Expressions.asDateTime(s.createdDate),
                                s.teamType
                        )
                ).from(s)
                .where(s.teamType.eq(teamType))
                .orderBy(s.createdDate.desc())
                .fetch();
        return list;
    }
}

