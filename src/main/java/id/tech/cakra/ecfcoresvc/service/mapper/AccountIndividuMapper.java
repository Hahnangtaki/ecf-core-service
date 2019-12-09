package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.AccountIndividuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountIndividu} and its DTO {@link AccountIndividuDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountMemberMapper.class})
public interface AccountIndividuMapper extends EntityMapper<AccountIndividuDTO, AccountIndividu> {

    @Mapping(source = "accountMember.id", target = "accountMemberId")
    AccountIndividuDTO toDto(AccountIndividu accountIndividu);

    @Mapping(source = "accountMemberId", target = "accountMember")
    AccountIndividu toEntity(AccountIndividuDTO accountIndividuDTO);

    default AccountIndividu fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountIndividu accountIndividu = new AccountIndividu();
        accountIndividu.setId(id);
        return accountIndividu;
    }
}
