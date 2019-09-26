package com.swizbizy.alfa.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "box")
public class Box {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "containedIn")
    private Long containedIn;

    public Box() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(id, box.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", containedIn=" + containedIn +
                '}';
    }
}
