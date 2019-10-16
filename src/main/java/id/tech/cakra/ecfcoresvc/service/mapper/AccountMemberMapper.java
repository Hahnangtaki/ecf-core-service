package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.AccountMemberDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountMember} and its DTO {@link AccountMemberDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountInstitutionMapper.class, AccountIndividuMapper.class, AccountBankMapper.class})
public interface AccountMemberMapper extends EntityMapper<AccountMemberDTO, AccountMember> {

    @Mapping(source = "accountInstitution.id", target = "accountInstitutionId")
    @Mapping(source = "accountIndividu.id", target = "accountIndividuId")
    @Mapping(source = "accountBank.id", target = "accountBankId")
    AccountMemberDTO toDto(AccountMember accountMember);

    @Mapping(source = "accountInstitutionId", target = "accountInstitution")
    @Mapping(source = "accountIndividuId", target = "accountIndividu")
    @Mapping(source = "accountBankId", target = "accountBank")
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
