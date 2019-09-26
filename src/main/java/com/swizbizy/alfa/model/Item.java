package com.swizbizy.alfa.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "containedIn")
    private Long containedIn;

    @Column(name = "color")
    private String color;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContainedIn() {
        return containedIn;
    }

    public void setContainedIn(Long containedIn) {
        this.containedIn = containedIn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", containedIn=" + containedIn +
                ", color='" + color + '\'' +
                '}';
    }
}
