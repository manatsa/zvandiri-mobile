package zw.org.zvandiri.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.MobilePhone;
import zw.org.zvandiri.business.domain.Patient;

@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, String> {
    MobilePhone getByCadre(Cadre cadre);
}
