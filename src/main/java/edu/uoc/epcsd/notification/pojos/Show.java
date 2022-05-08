package edu.uoc.epcsd.notification.pojos;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
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

    public Show() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return Float.compare(show.price, price) == 0 && duration == show.duration && capacity == show.capacity && Objects.equals(id, show.id) && Objects.equals(name, show.name) && Objects.equals(description, show.description) && Objects.equals(image, show.image) && Objects.equals(onSaleDate, show.onSaleDate) && Objects.equals(status, show.status) && Objects.equals(categories, show.categories) && Objects.equals(performances, show.performances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, image, price, duration, capacity, onSaleDate, status, categories, performances);
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", capacity=" + capacity +
                ", onSaleDate=" + onSaleDate +
                ", status='" + status + '\'' +
                ", categories=" + categories +
                ", performances=" + performances +
                '}';
    }
}
