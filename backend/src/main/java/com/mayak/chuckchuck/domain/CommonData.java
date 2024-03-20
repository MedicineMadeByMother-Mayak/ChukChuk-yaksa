package com.mayak.chuckchuck.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

//공통데이터
@Embeddable
public class CommonData {
    @Column(name="create_date")
    @NotNull
    private LocalDateTime createDate;  //생성일
    @Column(name="modify_date")
    private LocalDateTime modifyDate;  //수정일
    @Column(name="is_delete")
    @ColumnDefault("false")
    private Boolean isDelete = false;  //삭제 여부

    /** 부모객체 생성시 createDate를 자동으로 세팅함
     * @author common
     * @param
     * @return void
     */
    @PrePersist
    void preCreateDate(){
        this.createDate = LocalDateTime.now();
    }

    /** 부모객체 수정시 modifyDate를 자동으로 세팅함
     * @author common
     * @param
     * @return void
     */
    @PreUpdate
    void preModifyDate(){
        this.modifyDate = LocalDateTime.now();
    }

    /** isDelete 토글 기능
     * @author common
     * @param
     * @return void
     */
    public void toggleDelete(){
        this.isDelete = !this.isDelete;
    }
}
