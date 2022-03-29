package zw.org.zvandiri.business.service;

import zw.org.zvandiri.business.domain.Bicycle;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.Patient;

import java.util.Optional;

public interface BicycleService {
    Bicycle save(Bicycle bicycle);
    Bicycle getByCadre(Cadre cadre);
    Bicycle get(String id);
    Optional<Bicycle> getIfNotNull(String id);
}
