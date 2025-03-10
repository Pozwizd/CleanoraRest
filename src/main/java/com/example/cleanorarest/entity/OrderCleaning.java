package com.example.cleanorarest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class OrderCleaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Duration durationCleaning;
    private Integer numberUnits;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "cleaning_id", nullable = false)
    private Cleaning cleaning;
    @OneToMany(mappedBy = "orderCleaning", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlot> timeSlots = new ArrayList<>();

    public void addTimeSlot(TimeSlot timeSlot) {
        timeSlots.add(timeSlot);
        timeSlot.setOrderCleaning(this);
    }
    public void removeTimeSlot(TimeSlot timeSlot) {
        timeSlots.remove(timeSlot);
        timeSlot.setOrderCleaning(null);
    }
    public void durationCleaning() {
        double totalDurationMinutes = cleaning.getCleaningSpecifications().getTimeMultiplier() * numberUnits * 60;
        this.durationCleaning = Duration.ofMinutes((long) totalDurationMinutes);
    }
}

