package mars.ourmindmaze.repository.friend;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.domain.QFriend;
import mars.ourmindmaze.domain.QUser;
import mars.ourmindmaze.vo.FriendVO;

import java.util.List;

@RequiredArgsConstructor
public class FriendCustomRepositoryImpl implements FriendCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<FriendVO> findFriendList(Long userId) {
        QFriend f = QFriend.friend1;
        List<FriendVO> list = queryFactory.select(
                        Projections.constructor(
                                FriendVO.class,
                                f.id,
                                f.friend.id,
                                f.friend.username
                        )
                ).from(f)
                .orderBy(f.createdDate.desc())
                .leftJoin(QFriend.friend1.friend)
                .leftJoin(QFriend.friend1.user)
                .where(f.user.id.eq(userId)).fetch();
        return list;
    }

    @Override
    public List<FriendVO> findFriendRequestList(Long userId) {
        QFriend f = QFriend.friend1;
        List<FriendVO> list = queryFactory.select(
                        Projections.constructor(
                                FriendVO.class,
                                f.id,
                                f.friend.id,
                                f.friend.username
                        )
                ).from(f)
                .orderBy(f.createdDate.desc())
                .leftJoin(QFriend.friend1.friend)
                .where(f.status.eq("N"))
                .where(f.friend.id.eq(userId)).fetch();
        return list;
    }
}
