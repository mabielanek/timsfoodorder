package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.timsmeet.persistance.constants.DbTable;
import com.timsmeet.persistance.enums.ActivityStatus;

@Entity
@Table(name = DbTable.Address.TABLE)
public class AddressEntity {

    @Id
    @GeneratedValue(generator = "addressGenerator")
    @GenericGenerator(name = "addressGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_address_id")
            })
    private long id;

    @Version
    @Column(name = DbTable.Address.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.Address.STATUS, nullable = false, length = 1)
    @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
    private String status;

    @Column(name = DbTable.Address.ADDRESS1, length = 255)
    private String address1;

    @Column(name = DbTable.Address.ADDRESS2, length = 255)
    private String address2;

    @Column(name = DbTable.Address.CITY, length = 255)
    private String city;

    @Column(name = DbTable.Address.ZIP_CODE, length = 15)
    private String zipCode;

    @Column(name = DbTable.Address.STATE, length = 40)
    private String state;

    @Column(name = DbTable.Address.COUNTRY, length = 255)
    private String country;

    @Column(name = DbTable.Address.COMMENT_TEXT, length = 1024)
    private String comment;

    @Column(name = DbTable.Address.DISPLAY_INDEX, nullable = false)
    private int displayIndex;

    /**
     * Gets the record status.
     *
     * @return the status
     */
    public ActivityStatus getStatus() {
        return ActivityStatus.forCode(status);
    }

    /**
     * Sets the record status.
     *
     * @param status
     *            the new status
     */
    public void setStatus(ActivityStatus status) {
        this.status = status.getCode();
    }

    /**
     * Gets the address first line.
     *
     * @return the address first line
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the address first line.
     *
     * @param address1
     *            the new address first line
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * Gets the address second line.
     *
     * @return the address second line
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the address second line.
     *
     * @param address2
     *            the new address second line
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city
     *            the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code.
     *
     * @param zipCode
     *            the new zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state
     *            the new state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country
     *            the new country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment.
     *
     * @param comment
     *            the new comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the display index, when sorting is necessary for entity with
     * multiple addresses.
     *
     * @return the display index
     */
    public int getDisplayIndex() {
        return displayIndex;
    }

    /**
     * Sets the display index, when sorting is necessary for entity with
     * multiple addresses..
     *
     * @param displayIndex
     *            the new display index
     */
    public void setDisplayIndex(int displayIndex) {
        this.displayIndex = displayIndex;
    }

    /**
     * Gets the Address record identifier.
     *
     * @return the record identifier
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the last modification identifier - for optimistic concurrency
     * locking.
     *
     * @return the last modification id
     */
    public long getLastModificationId() {
        return lastModificationId;
    }

    /**
     * Sets the last modification identifier - for optimistic concurrency
     * locking.
     *
     * @param comment
     *            the last modification id
     */
    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

    public final static class Builder {
        private final AddressEntity entity = new AddressEntity();

        public Builder(ActivityStatus status, int displayIndex) {
            entity.setStatus(status);
            entity.setDisplayIndex(displayIndex);
        }

        public AddressEntity build() {
            return entity;
        }

        public Builder status(ActivityStatus status) {
            entity.setStatus(status);
            return this;
        }

        public Builder address1(String address1) {
            entity.setAddress1(address1);
            return this;
        }

        public Builder address2(String address2) {
            entity.setAddress2(address2);
            return this;
        }

        public Builder city(String city) {
            entity.setCity(city);
            return this;
        }

        public Builder zipCode(String zipCode) {
            entity.setZipCode(zipCode);
            return this;
        }

        public Builder state(String state) {
            entity.setState(state);
            return this;
        }

        public Builder country(String country) {
            entity.setCountry(country);
            return this;
        }

        public Builder comment(String comment) {
            entity.setComment(comment);
            return this;
        }

        public Builder displayIndex(int displayIndex) {
            entity.setDisplayIndex(displayIndex);
            return this;
        }

    }
}
