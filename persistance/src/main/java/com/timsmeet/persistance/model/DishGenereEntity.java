package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.timsmeet.persistance.constants.DbTable;

@Entity
@Table(name = DbTable.DishGenere.TABLE,
        indexes = {
                @Index(columnList = DbTable.DishGenere.DISH_ID, name = "idx_dish_gene_dish_fk"),
                @Index(columnList = DbTable.DishGenere.GENERE_ID, name = "idx_dish_gene_gene_fk") })
public class DishGenereEntity {

    @Id
    @GeneratedValue(generator = "dishGenereGenerator")
    @GenericGenerator(name = "dishGenereGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_dish_genere_id")
            })
    private long id;

    @Version
    @Column(name = DbTable.DishGenere.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @ManyToOne
    @JoinColumn(name = DbTable.DishGenere.DISH_ID, foreignKey = @ForeignKey(name = "dish_gene_dish_fk"))
    private DishEntity dish;

    @ManyToOne
    @JoinColumn(name = DbTable.DishGenere.GENERE_ID, foreignKey = @ForeignKey(name = "dish_gene_gene_fk"))
    private GenereEntity genere;

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    public GenereEntity getGenere() {
        return genere;
    }

    public void setGenere(GenereEntity genere) {
        this.genere = genere;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

}
