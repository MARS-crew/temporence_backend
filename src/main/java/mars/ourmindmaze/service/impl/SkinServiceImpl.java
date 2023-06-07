package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.repository.SkinJpaRepository;
import mars.ourmindmaze.service.SkinService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkinServiceImpl implements SkinService {
    private final SkinJpaRepository skinJpaRepository;
}
