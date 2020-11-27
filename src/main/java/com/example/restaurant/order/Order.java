package com.example.restaurant.order;

import com.example.restaurant.content.Content;
import com.example.restaurant.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(name = "order_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDateTime;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "table_id")
    private Integer tableId;

    @Column(name = "is_cooked")
    private Boolean isCooked;

    @Column(name = "is_paid")
    private Boolean isPaid;

    // EAGERLY FETCH CONTENTS
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Content> contents;

    protected Set<Content> getContentsInternal() {
        if (this.contents == null) {
            this.contents = new HashSet<>();
        }
        return this.contents;
    }

    protected void setContents(Set<Content> contents) {
        this.contents = contents;
    }

    public void addContent(Content content) {
        getContentsInternal().add(content);
        content.setOrder(this);
    }
}
