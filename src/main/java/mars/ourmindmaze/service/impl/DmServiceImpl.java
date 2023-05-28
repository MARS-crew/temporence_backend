package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.repository.dm.DmJpaRepository;
import mars.ourmindmaze.service.DmService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DmServiceImpl implements DmService {
    private final DmJpaRepository dmJpaRepository;
}
