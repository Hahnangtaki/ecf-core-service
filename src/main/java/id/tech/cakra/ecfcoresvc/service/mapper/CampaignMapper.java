package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.CampaignDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Campaign} and its DTO {@link CampaignDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompanyBankMapper.class, AccountMemberMapper.class})
public interface CampaignMapper extends EntityMapper<CampaignDTO, Campaign> {

    @Mapping(source = "companyBank.id", target = "companyBankId")
    @Mapping(source = "accountMember.id", target = "accountMemberId")
    CampaignDTO toDto(Campaign campaign);

    @Mapping(target = "campaignCategories", ignore = true)
    @Mapping(target = "removeCampaignCategory", ignore = true)
    @Mapping(target = "campaignInvestors", ignore = true)
    @Mapping(target = "removeCampaignInvestor", ignore = true)
    @Mapping(target = "campaignPayments", ignore = true)
    @Mapping(target = "removeCampaignPayment", ignore = true)
    @Mapping(target = "campaignTransactions", ignore = true)
    @Mapping(target = "removeCampaignTransaction", ignore = true)
    @Mapping(source = "companyBankId", target = "companyBank")
    @Mapping(source = "accountMemberId", target = "accountMember")
    Campaign toEntity(CampaignDTO campaignDTO);

    default Campaign fromId(Long id) {
        if (id == null) {
            return null;
        }
        Campaign campaign = new Campaign();
        campaign.setId(id);
        return campaign;
    }
}
