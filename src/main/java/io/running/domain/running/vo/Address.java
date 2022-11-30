package io.running.domain.running.vo;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Address {

    private String doName;

    private String sigungu;

    private String location;

}
