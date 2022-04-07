package cz.spring.demo.entities;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "timestamp")
    @NonNull
    private LocalDateTime timestamp;
    @Column(name = "type")
    @NonNull
    private String type;
    @Column(name = "actor")
    @NonNull
    private String actor;

    @ElementCollection(fetch = FetchType.LAZY)
    Map<String, String> data = new HashMap<>();

    public Transaction(){};

    public Transaction(LocalDateTime timestamp, String type, String actor, Map<String, String> data) {
        this.timestamp = timestamp;
        this.type = type;
        this.actor = actor;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id) && timestamp.equals(that.timestamp) && type.equals(that.type) && actor.equals(that.actor) && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, type, actor, data);
    }
}
