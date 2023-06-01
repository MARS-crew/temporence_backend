package mars.ourmindmaze.repository.item;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.domain.QItem;
import mars.ourmindmaze.vo.ItemVO;

import java.util.List;

@RequiredArgsConstructor
public class ItemCustomRepositoryImpl implements ItemCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ItemVO> findItemList() {
        QItem i = QItem.item1;
        List<ItemVO> list = queryFactory.select(
                        Projections.constructor(
                                ItemVO.class,
                                i.id,
                                i.itemType,
                                i.pointType,
                                i.cost,
                                i.item,
                                Expressions.asDateTime(i.createdDate)
                        )
                )
                .from(i)
                .orderBy(i.createdDate.desc()).fetch();

        return list;
    }
}
