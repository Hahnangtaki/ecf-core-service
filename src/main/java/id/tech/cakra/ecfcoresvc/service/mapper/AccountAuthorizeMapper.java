package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.AccountAuthorizeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountAuthorize} and its DTO {@link AccountAuthorizeDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountMemberMapper.class})
public interface AccountAuthorizeMapper extends EntityMapper<AccountAuthorizeDTO, AccountAuthorize> {

    @Mapping(source = "accountMember.id", target = "accountMemberId")
    AccountAuthorizeDTO toDto(AccountAuthorize accountAuthorize);

    @Mapping(source = "accountMemberId", target = "accountMember")
    AccountAuthorize toEntity(AccountAuthorizeDTO accountAuthorizeDTO);

    default AccountAuthorize fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountAuthorize accountAuthorize = new AccountAuthorize();
        accountAuthorize.setId(id);
        return accountAuthorize;
    }
}
