package com.timsmeet.persistance.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "fo_vacation",
        indexes = { @Index(columnList = "provider_id", name = "idx_vacation_provider_fk") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "owner_type", discriminatorType = DiscriminatorType.STRING, length = 1)
public class VacationEntity {

    @Id
    @GeneratedValue(generator = "vacationGenerator")
    @GenericGenerator(name = "vacationGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_vacation_id")
            })
    private long id;

    @Version
    @Column(name = "last_modification_id")
    private long lastModificationId;

    @Column(name = "start_day", nullable = false)
    private Timestamp startDay;

    @Column(name = "end_day", nullable = false)
    private Timestamp endDay;

    /**
     * Gets the start day.
     *
     * @return the start day
     */
    public Timestamp getStartDay() {
        return startDay;
    }

    /**
     * Sets the start day.
     *
     * @param startDay
     *            the new start day
     */
    public void setStartDay(Timestamp startDay) {
        this.startDay = startDay;
    }

    /**
     * Gets the end day.
     *
     * @return the end day
     */
    public Timestamp getEndDay() {
        return endDay;
    }

    /**
     * Sets the end day.
     *
     * @param endDay
     *            the new end day
     */
    public void setEndDay(Timestamp endDay) {
        this.endDay = endDay;
    }

    /**
     * Gets the Vacation record identifier.
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

    static class Builder<T extends VacationEntity> {

        private final T entity;

        public Builder(T entity) {
            this.entity = entity;
        }

        public Builder<T> startDay(Timestamp startDay) {
            entity.setStartDay(startDay);
            return this;
        }

        public Builder<T> endDay(Timestamp endDay) {
            entity.setEndDay(endDay);
            return this;
        }

        public T build() {
            return entity;
        }

    }

}
