package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.AccountMember;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountMemberRepository extends JpaRepository<AccountMember, Long> {

}
