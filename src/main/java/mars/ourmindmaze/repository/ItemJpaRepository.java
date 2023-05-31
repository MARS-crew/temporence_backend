package mars.ourmindmaze.repository;

import mars.ourmindmaze.domain.Item;
import mars.ourmindmaze.dto.item.RequestItemSaveDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemJpaRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByItem(String item);
}
