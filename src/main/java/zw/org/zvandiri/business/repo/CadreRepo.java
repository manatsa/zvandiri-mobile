package zw.org.zvandiri.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Assessment;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Province;

import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public interface CadreRepo extends  JpaRepository<Cadre, String> {

    @Query("Select Distinct p from Cadre p left join fetch p.createdBy left join fetch p.modifiedBy left join fetch p.primaryClinic left join fetch p.supportGroup where p.active=:active and (p.firstName Like %:name% or p.lastName Like %:name% ) order by p.lastName, p.firstName ASC")
    public List<Cadre> findByFirstNameOrLastName(@Param("name") String name, @Param("active") Boolean active);

    @Query("Select Distinct p from Cadre p left join fetch p.createdBy left join fetch p.modifiedBy  left join fetch p.primaryClinic left join fetch p.supportGroup where p.active=:active and (p.firstName Like :name% or p.lastName Like :name% ) and p.primaryClinic.district.province=:province order by p.lastName, p.firstName, p.middleName ASC")
    public List<Cadre> findByFirstNameOrLastNameAndProvince(@Param("name") String name, @Param("active") Boolean active, @Param("province") Province province);

    @Query("Select Distinct p from Cadre p left join fetch p.createdBy left join fetch p.modifiedBy left join fetch p.primaryClinic left join fetch p.supportGroup where p.active=:active and (p.firstName Like :name% or p.lastName Like :name%) and p.primaryClinic.district=:district order by p.lastName, p.firstName, p.middleName ASC")
    public List<Cadre> findByFirstNameOrLastNameAndDistrict(@Param("name") String name, @Param("active") Boolean active, @Param("district") District district);

    @Query("Select Distinct p from Cadre p left join fetch p.createdBy left join fetch p.modifiedBy  left join fetch p.primaryClinic left join fetch p.supportGroup  where p.active=:active and ((p.firstName Like %:first% or p.firstName Like %:last% ) and (p.lastName Like %:last% or p.lastName Like %:first% ) ) order by p.lastName, p.firstName ASC")
    public List<Cadre> findByFirstNameAndLastName(@Param("first") String first, @Param("last") String last, @Param("active") Boolean active);

    @Query("Select Distinct p from Cadre p left join fetch p.createdBy left join fetch p.modifiedBy  left join fetch p.primaryClinic left join fetch p.supportGroup  where p.active=:active and ((p.firstName Like :first% or p.firstName Like :last%) and (p.lastName Like :last% or p.lastName Like :first%) ) and p.primaryClinic.district.province=:province order by p.lastName, p.firstName, p.middleName ASC")
    public List<Cadre> findByFirstNameAndLastNameAndProvince(@Param("first") String first, @Param("last") String last, @Param("active") Boolean active, @Param("province") Province province);

    @Query("Select Distinct p from Cadre p left join fetch p.createdBy left join fetch p.modifiedBy  left join fetch p.primaryClinic left join fetch p.supportGroup  where p.active=:active and ((p.firstName Like :first% or p.firstName Like :last%) and (p.lastName Like :last% or p.lastName Like :first%) ) and p.primaryClinic.district=:district order by p.lastName, p.firstName, p.middleName ASC")
    public List<Cadre> findByFirstNameAndLastNameAndDistrict(@Param("first") String first, @Param("last") String last, @Param("active") Boolean active, @Param("district") District district);


}
