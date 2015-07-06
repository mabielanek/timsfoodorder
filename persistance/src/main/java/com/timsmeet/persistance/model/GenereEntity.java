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

@Entity
@Table(name = DbTable.Genere.TABLE)
public class GenereEntity {

    @Id
    @GeneratedValue(generator = "genereGenerator")
    @GenericGenerator(name = "genereGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_genere_id")
            })
    private long id;

    @Version
    @Column(name = DbTable.Genere.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.Genere.NAME, length = 255)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

}
