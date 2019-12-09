package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.AccountMemberDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountMember} and its DTO {@link AccountMemberDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountMemberMapper extends EntityMapper<AccountMemberDTO, AccountMember> {


    @Mapping(target = "campaignTransactions", ignore = true)
    @Mapping(target = "removeCampaignTransaction", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    @Mapping(target = "removeCampaign", ignore = true)
    @Mapping(target = "campaignInvestors", ignore = true)
    @Mapping(target = "removeCampaignInvestor", ignore = true)
    @Mapping(target = "accountAddresses", ignore = true)
    @Mapping(target = "removeAccountAddress", ignore = true)
    @Mapping(target = "accountAuthorizes", ignore = true)
    @Mapping(target = "removeAccountAuthorize", ignore = true)
    @Mapping(target = "accountInstitution", ignore = true)
    @Mapping(target = "accountIndividu", ignore = true)
    @Mapping(target = "accountBank", ignore = true)
    AccountMember toEntity(AccountMemberDTO accountMemberDTO);

    default AccountMember fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountMember accountMember = new AccountMember();
        accountMember.setId(id);
        return accountMember;
    }
}
