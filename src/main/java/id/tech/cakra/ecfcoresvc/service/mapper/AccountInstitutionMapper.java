package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.AccountInstitutionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountInstitution} and its DTO {@link AccountInstitutionDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountMemberMapper.class})
public interface AccountInstitutionMapper extends EntityMapper<AccountInstitutionDTO, AccountInstitution> {

    @Mapping(source = "accountMember.id", target = "accountMemberId")
    AccountInstitutionDTO toDto(AccountInstitution accountInstitution);

    @Mapping(source = "accountMemberId", target = "accountMember")
    AccountInstitution toEntity(AccountInstitutionDTO accountInstitutionDTO);

    default AccountInstitution fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountInstitution accountInstitution = new AccountInstitution();
        accountInstitution.setId(id);
        return accountInstitution;
    }
}
