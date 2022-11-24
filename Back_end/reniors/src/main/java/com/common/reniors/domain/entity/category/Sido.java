package com.common.reniors.domain.entity.category;

import com.common.reniors.common.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Sido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sido_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private Long code;

    @OneToMany(mappedBy = "sido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gugun> guguns = new ArrayList<>();

    public static Sido create(String name, Long code){
        Sido sido = new Sido();
        sido.name = name;
        sido.code = code;
        return sido;
    }

    public void update(String name, Long code){
        this.name = name;
        this.code = code;
    }

    public void addGugun(String gugunName, Long gugunCode){
        Gugun gugun = Gugun.create(gugunName, gugunCode, this);
        guguns.add(gugun);
    }

    public void deleteGugun(long gugunId){
        Gugun findGugun = guguns.stream()
                .filter(gugun -> gugun.getId()==(gugunId))
                .findFirst().orElseThrow(()->new NotFoundException("Not Found Gugun"));
        guguns.remove(findGugun);
    }
}
