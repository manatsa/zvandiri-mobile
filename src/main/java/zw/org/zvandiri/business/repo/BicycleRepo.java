package zw.org.zvandiri.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.org.zvandiri.business.domain.Assessment;
import zw.org.zvandiri.business.domain.Bicycle;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.Patient;

public interface BicycleRepo extends JpaRepository<Bicycle, String> {

    Bicycle getByCadre(Cadre cadre);
}
