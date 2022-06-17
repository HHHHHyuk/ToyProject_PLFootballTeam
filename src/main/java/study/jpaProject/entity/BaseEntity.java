package study.jpaProject.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)  //JPA 엔터티에서 persist, update, revmove, load에 대한 전후 과정에 콜백 메서드를 제공
@MappedSuperclass // 공통 매핑 정보를 구현하는 클래스에 선언한다. 가져오는 곳에서 extends로 가져온다.
@Getter
public class BaseEntity extends BaseTimeEntity{

    @CreatedBy
    @Column(updatable=false)
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;
}
