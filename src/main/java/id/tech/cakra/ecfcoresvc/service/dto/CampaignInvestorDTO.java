package id.tech.cakra.ecfcoresvc.service.dto;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.ecfcoresvc.domain.CampaignInvestor} entity.
 */
public class CampaignInvestorDTO implements Serializable {

    private Long id;

    private BigDecimal qtyOnHand;

    private BigDecimal qtyDeposit;

    private BigDecimal qtyWithdraw;

    private BigDecimal castValue;

    private BigDecimal profitLoss;

    private LocalDate createSystemDate;

    private ZonedDateTime createDate;

    private Long createUserId;

    private LocalDate lastModificationSystemDate;

    private ZonedDateTime lastModificationDate;

    private Long lastModificationUserId;


    private Long campaignId;

    private Long accountMemberId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(BigDecimal qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public BigDecimal getQtyDeposit() {
        return qtyDeposit;
    }

    public void setQtyDeposit(BigDecimal qtyDeposit) {
        this.qtyDeposit = qtyDeposit;
    }

    public BigDecimal getQtyWithdraw() {
        return qtyWithdraw;
    }

    public void setQtyWithdraw(BigDecimal qtyWithdraw) {
        this.qtyWithdraw = qtyWithdraw;
    }

    public BigDecimal getCastValue() {
        return castValue;
    }

    public void setCastValue(BigDecimal castValue) {
        this.castValue = castValue;
    }

    public BigDecimal getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(BigDecimal profitLoss) {
        this.profitLoss = profitLoss;
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

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
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

        CampaignInvestorDTO campaignInvestorDTO = (CampaignInvestorDTO) o;
        if (campaignInvestorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaignInvestorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampaignInvestorDTO{" +
            "id=" + getId() +
            ", qtyOnHand=" + getQtyOnHand() +
            ", qtyDeposit=" + getQtyDeposit() +
            ", qtyWithdraw=" + getQtyWithdraw() +
            ", castValue=" + getCastValue() +
            ", profitLoss=" + getProfitLoss() +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            ", campaign=" + getCampaignId() +
            ", accountMember=" + getAccountMemberId() +
            "}";
    }
}
