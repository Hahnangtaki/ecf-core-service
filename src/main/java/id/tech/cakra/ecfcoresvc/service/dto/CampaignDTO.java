package id.tech.cakra.ecfcoresvc.service.dto;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link id.tech.cakra.ecfcoresvc.domain.Campaign} entity.
 */
public class CampaignDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String campaignCode;

    @Size(max = 100)
    private String campaignName;

    @Size(max = 200)
    private String campaignDesc;

    @Size(max = 10)
    private String investmentType;

    private LocalDate offerDate;

    private LocalDate deadlineDate;

    private Double price;

    private Double fundTarget;

    private Double fundRaised;

    private Double fundPaid;

    private Long qtyTarget;

    private Long qtyRaised;

    private Double estRoiMin;

    private Double estRoiMax;

    @Lob
    private byte[] prospectusFile;

    private String prospectusFileContentType;
    @Lob
    private byte[] campaignLogo1;

    private String campaignLogo1ContentType;
    @Lob
    private byte[] campaignLogo2;

    private String campaignLogo2ContentType;
    @Lob
    private byte[] campaignLogo3;

    private String campaignLogo3ContentType;
    private String status;

    private Long currencyId;

    private LocalDate createSystemDate;

    private ZonedDateTime createDate;

    private Long createUserId;

    private LocalDate lastModificationSystemDate;

    private ZonedDateTime lastModificationDate;

    private Long lastModificationUserId;


    private Long companyBankId;

    private Long accountMemberId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignDesc() {
        return campaignDesc;
    }

    public void setCampaignDesc(String campaignDesc) {
        this.campaignDesc = campaignDesc;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getFundTarget() {
        return fundTarget;
    }

    public void setFundTarget(Double fundTarget) {
        this.fundTarget = fundTarget;
    }

    public Double getFundRaised() {
        return fundRaised;
    }

    public void setFundRaised(Double fundRaised) {
        this.fundRaised = fundRaised;
    }

    public Double getFundPaid() {
        return fundPaid;
    }

    public void setFundPaid(Double fundPaid) {
        this.fundPaid = fundPaid;
    }

    public Long getQtyTarget() {
        return qtyTarget;
    }

    public void setQtyTarget(Long qtyTarget) {
        this.qtyTarget = qtyTarget;
    }

    public Long getQtyRaised() {
        return qtyRaised;
    }

    public void setQtyRaised(Long qtyRaised) {
        this.qtyRaised = qtyRaised;
    }

    public Double getEstRoiMin() {
        return estRoiMin;
    }

    public void setEstRoiMin(Double estRoiMin) {
        this.estRoiMin = estRoiMin;
    }

    public Double getEstRoiMax() {
        return estRoiMax;
    }

    public void setEstRoiMax(Double estRoiMax) {
        this.estRoiMax = estRoiMax;
    }

    public byte[] getProspectusFile() {
        return prospectusFile;
    }

    public void setProspectusFile(byte[] prospectusFile) {
        this.prospectusFile = prospectusFile;
    }

    public String getProspectusFileContentType() {
        return prospectusFileContentType;
    }

    public void setProspectusFileContentType(String prospectusFileContentType) {
        this.prospectusFileContentType = prospectusFileContentType;
    }

    public byte[] getCampaignLogo1() {
        return campaignLogo1;
    }

    public void setCampaignLogo1(byte[] campaignLogo1) {
        this.campaignLogo1 = campaignLogo1;
    }

    public String getCampaignLogo1ContentType() {
        return campaignLogo1ContentType;
    }

    public void setCampaignLogo1ContentType(String campaignLogo1ContentType) {
        this.campaignLogo1ContentType = campaignLogo1ContentType;
    }

    public byte[] getCampaignLogo2() {
        return campaignLogo2;
    }

    public void setCampaignLogo2(byte[] campaignLogo2) {
        this.campaignLogo2 = campaignLogo2;
    }

    public String getCampaignLogo2ContentType() {
        return campaignLogo2ContentType;
    }

    public void setCampaignLogo2ContentType(String campaignLogo2ContentType) {
        this.campaignLogo2ContentType = campaignLogo2ContentType;
    }

    public byte[] getCampaignLogo3() {
        return campaignLogo3;
    }

    public void setCampaignLogo3(byte[] campaignLogo3) {
        this.campaignLogo3 = campaignLogo3;
    }

    public String getCampaignLogo3ContentType() {
        return campaignLogo3ContentType;
    }

    public void setCampaignLogo3ContentType(String campaignLogo3ContentType) {
        this.campaignLogo3ContentType = campaignLogo3ContentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public Long getCompanyBankId() {
        return companyBankId;
    }

    public void setCompanyBankId(Long companyBankId) {
        this.companyBankId = companyBankId;
    }

    public Long getAccountMemberId() {
        return accountMemberId;
    }

    public void setAccountMemberId(Long accountMemberId) {
        this.accountMemberId = accountMemberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CampaignDTO campaignDTO = (CampaignDTO) o;
        if (campaignDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaignDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampaignDTO{" +
            "id=" + getId() +
            ", campaignCode='" + getCampaignCode() + "'" +
            ", campaignName='" + getCampaignName() + "'" +
            ", campaignDesc='" + getCampaignDesc() + "'" +
            ", investmentType='" + getInvestmentType() + "'" +
            ", offerDate='" + getOfferDate() + "'" +
            ", deadlineDate='" + getDeadlineDate() + "'" +
            ", price=" + getPrice() +
            ", fundTarget=" + getFundTarget() +
            ", fundRaised=" + getFundRaised() +
            ", fundPaid=" + getFundPaid() +
            ", qtyTarget=" + getQtyTarget() +
            ", qtyRaised=" + getQtyRaised() +
            ", estRoiMin=" + getEstRoiMin() +
            ", estRoiMax=" + getEstRoiMax() +
            ", prospectusFile='" + getProspectusFile() + "'" +
            ", campaignLogo1='" + getCampaignLogo1() + "'" +
            ", campaignLogo2='" + getCampaignLogo2() + "'" +
            ", campaignLogo3='" + getCampaignLogo3() + "'" +
            ", status='" + getStatus() + "'" +
            ", currencyId=" + getCurrencyId() +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            ", companyBank=" + getCompanyBankId() +
            ", accountMember=" + getAccountMemberId() +
            "}";
    }
}
