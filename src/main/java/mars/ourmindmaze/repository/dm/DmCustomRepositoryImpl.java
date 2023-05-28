package mars.ourmindmaze.repository.dm;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.domain.QDM;
import mars.ourmindmaze.vo.DmVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class DmCustomRepositoryImpl implements DmCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<DmVO> findDmList(Long sendId, Long reciverId) {
        QDM d = QDM.dM;

        List<DmVO> list = queryFactory.select(
                        Projections.constructor(
                                DmVO.class,
                                d.id,
                                d.sender.username,
                                d.reciver.username,
                                d.content,
                                Expressions.asDateTime(d.createdDate)
                        )
                )
                .from(d)
                .where(d.sender.id.eq(sendId))
                .where(d.reciver.id.eq(reciverId))
                .orderBy(d.createdDate.desc()).fetch();

        return list;
    }
}
