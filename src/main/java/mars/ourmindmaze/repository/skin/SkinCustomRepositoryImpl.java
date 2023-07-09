package mars.ourmindmaze.repository.skin;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.domain.QSkin;
import mars.ourmindmaze.enums.TeamType;
import mars.ourmindmaze.vo.SkinVO;

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

