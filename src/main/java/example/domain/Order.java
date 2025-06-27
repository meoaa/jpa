package example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    private Long id;

    private String item;

    @ManyToOne
    private Member member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
