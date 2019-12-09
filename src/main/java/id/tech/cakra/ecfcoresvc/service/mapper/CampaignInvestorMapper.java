package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.CampaignInvestorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampaignInvestor} and its DTO {@link CampaignInvestorDTO}.
 */
@Mapper(componentModel = "spring", uses = {CampaignMapper.class, AccountMemberMapper.class})
public interface CampaignInvestorMapper extends EntityMapper<CampaignInvestorDTO, CampaignInvestor> {

    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "accountMember.id", target = "accountMemberId")
    CampaignInvestorDTO toDto(CampaignInvestor campaignInvestor);

    @Mapping(source = "campaignId", target = "campaign")
    @Mapping(source = "accountMemberId", target = "accountMember")
    CampaignInvestor toEntity(CampaignInvestorDTO campaignInvestorDTO);

    default CampaignInvestor fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampaignInvestor campaignInvestor = new CampaignInvestor();
        campaignInvestor.setId(id);
        return campaignInvestor;
    }
}
