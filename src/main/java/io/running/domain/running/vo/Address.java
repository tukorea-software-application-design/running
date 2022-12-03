package io.running.domain.running.vo;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
public class Address {

    private String doName;

    private String sigungu;

    private String location;

}
