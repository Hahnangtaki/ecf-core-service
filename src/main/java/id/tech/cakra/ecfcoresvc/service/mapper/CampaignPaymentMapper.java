package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.CampaignPaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampaignPayment} and its DTO {@link CampaignPaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountBankMapper.class, CompanyBankMapper.class, CampaignMapper.class})
public interface CampaignPaymentMapper extends EntityMapper<CampaignPaymentDTO, CampaignPayment> {

    @Mapping(source = "accountBank.id", target = "accountBankId")
    @Mapping(source = "companyBank.id", target = "companyBankId")
    @Mapping(source = "campaign.id", target = "campaignId")
    CampaignPaymentDTO toDto(CampaignPayment campaignPayment);

    @Mapping(source = "accountBankId", target = "accountBank")
    @Mapping(source = "companyBankId", target = "companyBank")
    @Mapping(source = "campaignId", target = "campaign")
    CampaignPayment toEntity(CampaignPaymentDTO campaignPaymentDTO);

    default CampaignPayment fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampaignPayment campaignPayment = new CampaignPayment();
        campaignPayment.setId(id);
        return campaignPayment;
    }
}
