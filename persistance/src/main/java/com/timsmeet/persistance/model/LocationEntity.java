package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.timsmeet.persistance.constants.DbTable;

@Entity
@Table(name = DbTable.Location.TABLE)
public class LocationEntity {

    @Id
    @GeneratedValue(generator = "localizationGenerator")
    @GenericGenerator(name = "localizationGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_fo_localization_id") })
    private long id;

    @Version
    @Column(name = DbTable.Location.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.Location.NAME, nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = DbTable.Location.ORGANIZATION_ID, foreignKey = @ForeignKey(name = "location_organization_fk"))
    private OrganizationEntity organization;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

}
