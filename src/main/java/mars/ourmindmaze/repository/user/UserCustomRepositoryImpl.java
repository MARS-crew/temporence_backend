package mars.ourmindmaze.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.domain.QUser;
import mars.ourmindmaze.domain.User;

import java.util.List;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<User> findAll() {
        QUser m = QUser.user;
        List<User> result = queryFactory.selectFrom(m).fetch();
        return result;
    }
}
