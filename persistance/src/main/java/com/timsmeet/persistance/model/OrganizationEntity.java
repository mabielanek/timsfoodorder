package com.timsmeet.persistance.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.timsmeet.persistance.constants.DbTable;

@Entity
@Table(name = DbTable.Organization.TABLE)
public class OrganizationEntity {

    @Id
    @GeneratedValue(generator = "organizationGenerator")
    @GenericGenerator(name = "organizationGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_fo_organization_id") })
    private long id;

    @Version
    @Column(name = DbTable.Organization.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.Organization.NAME, nullable = false, length = 255)
    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = DbTable.Location.ORGANIZATION_ID)
    private List<LocationEntity> locations = new ArrayList<LocationEntity>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<LocationEntity> getLocations() {
        return locations;
    }

    public boolean addLocation(LocationEntity location) {
        if (this.locations == null) {
            this.locations = Lists.newArrayList();
        }
        location.setOrganization(this);
        return this.locations.add(location);
    }

    public boolean removeLocation(LocationEntity location) {
        Preconditions.checkNotNull(location);
        location.setOrganization(null);
        if (this.locations != null) {
            return this.locations.remove(location);
        }
        return false;
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
