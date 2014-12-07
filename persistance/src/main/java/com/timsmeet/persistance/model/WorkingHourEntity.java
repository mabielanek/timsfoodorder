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

import com.timsmeet.persistance.enums.WeekDay;

@Entity
@Table(name = "fo_working_hour",
  indexes = {@Index(columnList="provider_id", name="idx_wrk_hour_provider_fk")}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "owner_type", discriminatorType = DiscriminatorType.STRING, length = 1)
public class WorkingHourEntity {
  
  @Id
  @GeneratedValue(generator = "workingHourGenerator")
  @GenericGenerator(name = "workingHourGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_fo_working_hours_id")
  })
  private long id;
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;
  @Column(name = "week_day", nullable = false, length = 15)
  private String weekDay;
  @Column(name = "start_time", nullable = false)
  private Timestamp startTime;
  @Column(name = "end_time", nullable = false)
  private Timestamp endTime;
  
  /**
   * Gets the week day.
   *
   * @return the week day
   */
  public WeekDay getWeekDay() {
    return WeekDay.forCode(weekDay);
  }
  
  /**
   * Sets the week day.
   *
   * @param weekDay the new week day
   */
  public void setWeekDay(WeekDay weekDay) {
    this.weekDay = weekDay.getCode();
  }
  
  /**
   * Gets the start time.
   *
   * @return the start time
   */
  public Timestamp getStartTime() {
    return startTime;
  }
  
  /**
   * Sets the start time.
   *
   * @param startTime the new start time
   */
  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }
  
  /**
   * Gets the end time.
   *
   * @return the end time
   */
  public Timestamp getEndTime() {
    return endTime;
  }
  
  /**
   * Sets the end time.
   *
   * @param endTime the new end time
   */
  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  /**
   * Gets the Web Url record identifier.
   *
   * @return the record identifier
   */
  public long getId() {
    return id;
  }

  /**
   * Gets the last modification identifier - for optimistic concurrency locking.
   *
   * @return the last modification id
   */
  public long getLastModificationId() {
    return lastModificationId;
  }

  /**
   * Sets the last modification identifier - for optimistic concurrency locking.
   *
   * @param comment the last modification id
   */
  public void setLastModificationId(long lastModificationId) {
	  this.lastModificationId = lastModificationId;
  }  

  static class Builder<T extends WorkingHourEntity> {
	  
	  private final T entity;
	  
	  public Builder(T entity) {
	    this.entity = entity;
	  }

	  public Builder<T> weekDay(WeekDay weekDay) {
	    entity.setWeekDay(weekDay);
	    return this;
	  }

	  public Builder<T> startTime(Timestamp startTime) {
	    entity.setStartTime(startTime);
	    return this;
	  }

	  public Builder<T> endTime(Timestamp endTime) {
	    entity.setEndTime(endTime);
	    return this;
	  }
	  
	  public T build() {
	    return entity;
	  }
  }
}
