package org.sk.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@javax.persistence.Entity
@Table(name = "sk_example_table", schema = "public", catalog = "sk_example_db")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class Entity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Type(type = "json")
    @Column(name = "obj", nullable = false, columnDefinition = "jsonb")
    private Map<String, Integer> obj = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrent() {
        return obj.get("current");
    }

    public void setCurrent(int number) {
        Map<String, Integer> newObj = new HashMap<>();
        newObj.put("current", number);
        this.setObj(newObj);
    }
    public Map<String, Integer> getObj() {
        return obj;
    }

    public void setObj(Map<String, Integer> obj) {
        this.obj = obj;
    }
}
