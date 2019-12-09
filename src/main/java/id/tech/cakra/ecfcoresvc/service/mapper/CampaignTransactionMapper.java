package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.CampaignTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampaignTransaction} and its DTO {@link CampaignTransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CampaignMapper.class, AccountMemberMapper.class})
public interface CampaignTransactionMapper extends EntityMapper<CampaignTransactionDTO, CampaignTransaction> {

    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "accountMember.id", target = "accountMemberId")
    CampaignTransactionDTO toDto(CampaignTransaction campaignTransaction);

    @Mapping(source = "campaignId", target = "campaign")
    @Mapping(source = "accountMemberId", target = "accountMember")
    CampaignTransaction toEntity(CampaignTransactionDTO campaignTransactionDTO);

    default CampaignTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampaignTransaction campaignTransaction = new CampaignTransaction();
        campaignTransaction.setId(id);
        return campaignTransaction;
    }
}
