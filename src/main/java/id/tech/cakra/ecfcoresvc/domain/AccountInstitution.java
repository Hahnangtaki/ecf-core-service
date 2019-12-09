package id.tech.cakra.ecfcoresvc.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A AccountInstitution.
 */
@Entity
@Table(name = "account_institution")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountInstitution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 15)
    @Column(name = "sid", length = 15)
    private String sid;

    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    @Size(max = 20)
    @Column(name = "bic_code", length = 20)
    private String bicCode;

    @Size(max = 100)
    @Column(name = "npwp", length = 100)
    private String npwp;

    @Column(name = "npwp_reg_date")
    private LocalDate npwpRegDate;

    @Size(max = 30)
    @Column(name = "skd", length = 30)
    private String skd;

    @Column(name = "skd_exp_date")
    private LocalDate skdExpDate;

    @Size(max = 100)
    @Column(name = "company_establish_place", length = 100)
    private String companyEstablishPlace;

    @Column(name = "company_establish_date")
    private LocalDate companyEstablishDate;

    @Size(max = 1)
    @Column(name = "business_type", length = 1)
    private String businessType;

    @Size(max = 1)
    @Column(name = "company_characteristic", length = 1)
    private String companyCharacteristic;

    @Size(max = 30)
    @Column(name = "fund_source", length = 30)
    private String fundSource;

    @Size(max = 120)
    @Column(name = "fund_source_text", length = 120)
    private String fundSourceText;

    @Size(max = 120)
    @Column(name = "article_association", length = 120)
    private String articleAssociation;

    @Size(max = 120)
    @Column(name = "business_reg_no", length = 120)
    private String businessRegNo;

    @Column(name = "financial_asset_1", precision = 21, scale = 2)
    private BigDecimal financialAsset1;

    @Column(name = "financial_asset_2", precision = 21, scale = 2)
    private BigDecimal financialAsset2;

    @Column(name = "financial_asset_3", precision = 21, scale = 2)
    private BigDecimal financialAsset3;

    @Column(name = "operating_profit_1", precision = 21, scale = 2)
    private BigDecimal operatingProfit1;

    @Column(name = "operating_profit_2", precision = 21, scale = 2)
    private BigDecimal operatingProfit2;

    @Column(name = "operating_profit_3", precision = 21, scale = 2)
    private BigDecimal operatingProfit3;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Size(max = 10)
    @Column(name = "investment_objective", length = 10)
    private String investmentObjective;

    @Size(max = 15)
    @Column(name = "direct_sid", length = 15)
    private String directSID;

    @Size(max = 1)
    @Column(name = "asset_owner", length = 1)
    private String assetOwner;

    @Size(max = 1)
    @Column(name = "sup_doc_type", length = 1)
    private String supDocType;

    @Column(name = "sup_doc_exp_date")
    private LocalDate supDocExpDate;

    @Column(name = "tax_id")
    private Long taxId;

    @Column(name = "legal_domicile_id")
    private Long legalDomicileId;

    @Column(name = "create_system_date")
    private LocalDate createSystemDate;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "last_modification_system_date")
    private LocalDate lastModificationSystemDate;

    @Column(name = "last_modification_date")
    private ZonedDateTime lastModificationDate;

    @Column(name = "last_modification_user_id")
    private Long lastModificationUserId;

    @OneToOne
    @JoinColumn(unique = true)
    private AccountMember accountMember;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public AccountInstitution sid(String sid) {
        this.sid = sid;
        return this;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public AccountInstitution companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBicCode() {
        return bicCode;
    }

    public AccountInstitution bicCode(String bicCode) {
        this.bicCode = bicCode;
        return this;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }

    public String getNpwp() {
        return npwp;
    }

    public AccountInstitution npwp(String npwp) {
        this.npwp = npwp;
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public AccountInstitution npwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
        return this;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getSkd() {
        return skd;
    }

    public AccountInstitution skd(String skd) {
        this.skd = skd;
        return this;
    }

    public void setSkd(String skd) {
        this.skd = skd;
    }

    public LocalDate getSkdExpDate() {
        return skdExpDate;
    }

    public AccountInstitution skdExpDate(LocalDate skdExpDate) {
        this.skdExpDate = skdExpDate;
        return this;
    }

    public void setSkdExpDate(LocalDate skdExpDate) {
        this.skdExpDate = skdExpDate;
    }

    public String getCompanyEstablishPlace() {
        return companyEstablishPlace;
    }

    public AccountInstitution companyEstablishPlace(String companyEstablishPlace) {
        this.companyEstablishPlace = companyEstablishPlace;
        return this;
    }

    public void setCompanyEstablishPlace(String companyEstablishPlace) {
        this.companyEstablishPlace = companyEstablishPlace;
    }

    public LocalDate getCompanyEstablishDate() {
        return companyEstablishDate;
    }

    public AccountInstitution companyEstablishDate(LocalDate companyEstablishDate) {
        this.companyEstablishDate = companyEstablishDate;
        return this;
    }

    public void setCompanyEstablishDate(LocalDate companyEstablishDate) {
        this.companyEstablishDate = companyEstablishDate;
    }

    public String getBusinessType() {
        return businessType;
    }

    public AccountInstitution businessType(String businessType) {
        this.businessType = businessType;
        return this;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyCharacteristic() {
        return companyCharacteristic;
    }

    public AccountInstitution companyCharacteristic(String companyCharacteristic) {
        this.companyCharacteristic = companyCharacteristic;
        return this;
    }

    public void setCompanyCharacteristic(String companyCharacteristic) {
        this.companyCharacteristic = companyCharacteristic;
    }

    public String getFundSource() {
        return fundSource;
    }

    public AccountInstitution fundSource(String fundSource) {
        this.fundSource = fundSource;
        return this;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public String getFundSourceText() {
        return fundSourceText;
    }

    public AccountInstitution fundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
        return this;
    }

    public void setFundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
    }

    public String getArticleAssociation() {
        return articleAssociation;
    }

    public AccountInstitution articleAssociation(String articleAssociation) {
        this.articleAssociation = articleAssociation;
        return this;
    }

    public void setArticleAssociation(String articleAssociation) {
        this.articleAssociation = articleAssociation;
    }

    public String getBusinessRegNo() {
        return businessRegNo;
    }

    public AccountInstitution businessRegNo(String businessRegNo) {
        this.businessRegNo = businessRegNo;
        return this;
    }

    public void setBusinessRegNo(String businessRegNo) {
        this.businessRegNo = businessRegNo;
    }

    public BigDecimal getFinancialAsset1() {
        return financialAsset1;
    }

    public AccountInstitution financialAsset1(BigDecimal financialAsset1) {
        this.financialAsset1 = financialAsset1;
        return this;
    }

    public void setFinancialAsset1(BigDecimal financialAsset1) {
        this.financialAsset1 = financialAsset1;
    }

    public BigDecimal getFinancialAsset2() {
        return financialAsset2;
    }

    public AccountInstitution financialAsset2(BigDecimal financialAsset2) {
        this.financialAsset2 = financialAsset2;
        return this;
    }

    public void setFinancialAsset2(BigDecimal financialAsset2) {
        this.financialAsset2 = financialAsset2;
    }

    public BigDecimal getFinancialAsset3() {
        return financialAsset3;
    }

    public AccountInstitution financialAsset3(BigDecimal financialAsset3) {
        this.financialAsset3 = financialAsset3;
        return this;
    }

    public void setFinancialAsset3(BigDecimal financialAsset3) {
        this.financialAsset3 = financialAsset3;
    }

    public BigDecimal getOperatingProfit1() {
        return operatingProfit1;
    }

    public AccountInstitution operatingProfit1(BigDecimal operatingProfit1) {
        this.operatingProfit1 = operatingProfit1;
        return this;
    }

    public void setOperatingProfit1(BigDecimal operatingProfit1) {
        this.operatingProfit1 = operatingProfit1;
    }

    public BigDecimal getOperatingProfit2() {
        return operatingProfit2;
    }

    public AccountInstitution operatingProfit2(BigDecimal operatingProfit2) {
        this.operatingProfit2 = operatingProfit2;
        return this;
    }

    public void setOperatingProfit2(BigDecimal operatingProfit2) {
        this.operatingProfit2 = operatingProfit2;
    }

    public BigDecimal getOperatingProfit3() {
        return operatingProfit3;
    }

    public AccountInstitution operatingProfit3(BigDecimal operatingProfit3) {
        this.operatingProfit3 = operatingProfit3;
        return this;
    }

    public void setOperatingProfit3(BigDecimal operatingProfit3) {
        this.operatingProfit3 = operatingProfit3;
    }

    public String getDescription() {
        return description;
    }

    public AccountInstitution description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestmentObjective() {
        return investmentObjective;
    }

    public AccountInstitution investmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
        return this;
    }

    public void setInvestmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
    }

    public String getDirectSID() {
        return directSID;
    }

    public AccountInstitution directSID(String directSID) {
        this.directSID = directSID;
        return this;
    }

    public void setDirectSID(String directSID) {
        this.directSID = directSID;
    }

    public String getAssetOwner() {
        return assetOwner;
    }

    public AccountInstitution assetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
        return this;
    }

    public void setAssetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
    }

    public String getSupDocType() {
        return supDocType;
    }

    public AccountInstitution supDocType(String supDocType) {
        this.supDocType = supDocType;
        return this;
    }

    public void setSupDocType(String supDocType) {
        this.supDocType = supDocType;
    }

    public LocalDate getSupDocExpDate() {
        return supDocExpDate;
    }

    public AccountInstitution supDocExpDate(LocalDate supDocExpDate) {
        this.supDocExpDate = supDocExpDate;
        return this;
    }

    public void setSupDocExpDate(LocalDate supDocExpDate) {
        this.supDocExpDate = supDocExpDate;
    }

    public Long getTaxId() {
        return taxId;
    }

    public AccountInstitution taxId(Long taxId) {
        this.taxId = taxId;
        return this;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Long getLegalDomicileId() {
        return legalDomicileId;
    }

    public AccountInstitution legalDomicileId(Long legalDomicileId) {
        this.legalDomicileId = legalDomicileId;
        return this;
    }

    public void setLegalDomicileId(Long legalDomicileId) {
        this.legalDomicileId = legalDomicileId;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public AccountInstitution createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public AccountInstitution createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public AccountInstitution createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public AccountInstitution lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public AccountInstitution lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public AccountInstitution lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public AccountMember getAccountMember() {
        return accountMember;
    }

    public AccountInstitution accountMember(AccountMember accountMember) {
        this.accountMember = accountMember;
        return this;
    }

    public void setAccountMember(AccountMember accountMember) {
        this.accountMember = accountMember;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountInstitution)) {
            return false;
        }
        return id != null && id.equals(((AccountInstitution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AccountInstitution{" +
            "id=" + getId() +
            ", sid='" + getSid() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", bicCode='" + getBicCode() + "'" +
            ", npwp='" + getNpwp() + "'" +
            ", npwpRegDate='" + getNpwpRegDate() + "'" +
            ", skd='" + getSkd() + "'" +
            ", skdExpDate='" + getSkdExpDate() + "'" +
            ", companyEstablishPlace='" + getCompanyEstablishPlace() + "'" +
            ", companyEstablishDate='" + getCompanyEstablishDate() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", companyCharacteristic='" + getCompanyCharacteristic() + "'" +
            ", fundSource='" + getFundSource() + "'" +
            ", fundSourceText='" + getFundSourceText() + "'" +
            ", articleAssociation='" + getArticleAssociation() + "'" +
            ", businessRegNo='" + getBusinessRegNo() + "'" +
            ", financialAsset1=" + getFinancialAsset1() +
            ", financialAsset2=" + getFinancialAsset2() +
            ", financialAsset3=" + getFinancialAsset3() +
            ", operatingProfit1=" + getOperatingProfit1() +
            ", operatingProfit2=" + getOperatingProfit2() +
            ", operatingProfit3=" + getOperatingProfit3() +
            ", description='" + getDescription() + "'" +
            ", investmentObjective='" + getInvestmentObjective() + "'" +
            ", directSID='" + getDirectSID() + "'" +
            ", assetOwner='" + getAssetOwner() + "'" +
            ", supDocType='" + getSupDocType() + "'" +
            ", supDocExpDate='" + getSupDocExpDate() + "'" +
            ", taxId=" + getTaxId() +
            ", legalDomicileId=" + getLegalDomicileId() +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
