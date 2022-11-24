package com.common.reniors.domain.entity.category;

import com.common.reniors.domain.entity.recommend.RecommendCondition;
import com.common.reniors.domain.entity.HopeArea;
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
public class Gugun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gugun_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private Long code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_id")
    private Sido sido;

    @OneToMany(mappedBy = "gugun")
    private List<RecommendCondition> recommendConditions = new ArrayList<>();

    @OneToMany(mappedBy = "gugun")
    private  List<HopeArea> hopeAreas = new ArrayList<>();

    public static Gugun create(String name, Long code, Sido sido){
        Gugun gugun = new Gugun();
        gugun.name = name;
        gugun.code = code;
        gugun.sido = sido;
        return gugun;
    }

    public void update(String name, Long code, Sido sido){
        this.name = name;
        this.code = code;
        this.sido = sido;
    }

}
