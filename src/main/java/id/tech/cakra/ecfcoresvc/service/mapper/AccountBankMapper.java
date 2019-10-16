package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.AccountBankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountBank} and its DTO {@link AccountBankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountBankMapper extends EntityMapper<AccountBankDTO, AccountBank> {


    @Mapping(target = "campaignPayments", ignore = true)
    @Mapping(target = "removeCampaignPayment", ignore = true)
    @Mapping(target = "accountMember", ignore = true)
    AccountBank toEntity(AccountBankDTO accountBankDTO);

    default AccountBank fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountBank accountBank = new AccountBank();
        accountBank.setId(id);
        return accountBank;
    }
}
