package services;

import models.Canal;

import java.util.List;

public interface CanalService {
    Canal addCanal(Canal canal);
    void updateCanal(Canal canal);
    boolean deleteCanal(Long id);
    List<Canal> getAllCanals();
    Canal getCanal(Long id);
}
