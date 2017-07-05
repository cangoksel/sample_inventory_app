
package kur;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <p>Java class for CurrencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CurrencyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Unit" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Isim" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ForexBuying" type="{}decimal-or-empty"/>
 *         &lt;element name="ForexSelling" type="{}decimal-or-empty"/>
 *         &lt;element name="BanknoteBuying" type="{}decimal-or-empty"/>
 *         &lt;element name="BanknoteSelling" type="{}decimal-or-empty"/>
 *         &lt;element name="CrossRateUSD" type="{}decimal-or-empty"/>
 *         &lt;element name="CrossRateOther" type="{}decimal-or-empty"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CrossOrder" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Kod" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurrencyType", propOrder = {
    "unit",
    "isim",
    "currencyName",
    "forexBuying",
    "forexSelling",
    "banknoteBuying",
    "banknoteSelling",
    "crossRateUSD",
    "crossRateOther"
})
public class CurrencyType {

    @XmlElement(name = "Unit", required = true)
    protected BigInteger unit;
    @XmlElement(name = "Isim", required = true)
    protected String isim;
    @XmlElement(name = "CurrencyName", required = true)
    protected String currencyName;
    @XmlElement(name = "ForexBuying", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String forexBuying;
    @XmlElement(name = "ForexSelling", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String forexSelling;
    @XmlElement(name = "BanknoteBuying", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String banknoteBuying;
    @XmlElement(name = "BanknoteSelling", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String banknoteSelling;
    @XmlElement(name = "CrossRateUSD", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String crossRateUSD;
    @XmlElement(name = "CrossRateOther", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String crossRateOther;
    @XmlAttribute(name = "CrossOrder")
    protected Long crossOrder;
    @XmlAttribute(name = "Kod")
    protected String kod;
    @XmlAttribute(name = "CurrencyCode")
    protected String currencyCode;

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUnit(BigInteger value) {
        this.unit = value;
    }

    /**
     * Gets the value of the isim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsim() {
        return isim;
    }

    /**
     * Sets the value of the isim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsim(String value) {
        this.isim = value;
    }

    /**
     * Gets the value of the currencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Sets the value of the currencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyName(String value) {
        this.currencyName = value;
    }

    /**
     * Gets the value of the forexBuying property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigDecimal getForexBuying() {
        return new BigDecimal(forexBuying);
    }

    /**
     * Sets the value of the forexBuying property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForexBuying(BigDecimal value) {
        this.forexBuying = value == null ? null : value.toPlainString();
    }

    /**
     * Gets the value of the forexSelling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigDecimal getForexSelling() {
        return new BigDecimal(forexSelling);
    }

    /**
     * Sets the value of the forexSelling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForexSelling(BigDecimal value) {
        this.forexSelling = value == null ? null : value.toPlainString();
    }

    /**
     * Gets the value of the banknoteBuying property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigDecimal getBanknoteBuying() {
        return new BigDecimal(banknoteBuying);
    }

    /**
     * Sets the value of the banknoteBuying property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanknoteBuying(BigDecimal value) {
        this.banknoteBuying = value == null ? null : value.toPlainString();
    }

    /**
     * Gets the value of the banknoteSelling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigDecimal getBanknoteSelling() {
        return new BigDecimal(banknoteSelling);
    }

    /**
     * Sets the value of the banknoteSelling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanknoteSelling(BigDecimal value) {
        this.banknoteSelling = value == null ? null : value.toPlainString();
    }

    /**
     * Gets the value of the crossRateUSD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigDecimal getCrossRateUSD() {
        return new BigDecimal(crossRateUSD);
    }

    /**
     * Sets the value of the crossRateUSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrossRateUSD(BigDecimal value) {
        this.crossRateUSD = value == null ? null : value.toPlainString();
    }

    /**
     * Gets the value of the crossRateOther property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigDecimal getCrossRateOther() {
        return new BigDecimal(crossRateOther);
    }

    /**
     * Sets the value of the crossRateOther property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrossRateOther(BigDecimal value) {
        this.crossRateOther = value == null ? null : value.toPlainString();
    }

    /**
     * Gets the value of the crossOrder property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public Long getCrossOrder() {
        return crossOrder;
    }

    /**
     * Sets the value of the crossOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCrossOrder(Long value) {
        this.crossOrder = value;
    }

    /**
     * Gets the value of the kod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKod() {
        return kod;
    }

    /**
     * Sets the value of the kod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKod(String value) {
        this.kod = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

}
