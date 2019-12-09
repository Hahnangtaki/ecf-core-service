package id.tech.cakra.ecfcoresvc.service.mapper;

import id.tech.cakra.ecfcoresvc.domain.*;
import id.tech.cakra.ecfcoresvc.service.dto.CompanyBankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CompanyBank} and its DTO {@link CompanyBankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompanyBankMapper extends EntityMapper<CompanyBankDTO, CompanyBank> {


    @Mapping(target = "campaigns", ignore = true)
    @Mapping(target = "removeCampaign", ignore = true)
    @Mapping(target = "campaignPayments", ignore = true)
    @Mapping(target = "removeCampaignPayment", ignore = true)
    CompanyBank toEntity(CompanyBankDTO companyBankDTO);

    default CompanyBank fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyBank companyBank = new CompanyBank();
        companyBank.setId(id);
        return companyBank;
    }
}
