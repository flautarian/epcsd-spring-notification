package edu.uoc.epcsd.notification.pojos;

import lombok.*;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {
    private Long id;
    private String name;
    private String description;
    private String image;
    private float price;
    private int duration;
    private int capacity;
    private Date onSaleDate;
    private String status;
    private List<Category> categories;
    private List<Performance> performances;
}
