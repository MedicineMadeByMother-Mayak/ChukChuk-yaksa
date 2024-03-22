package com.mayak.chuckchuck.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="tag")
//태그
public class Tag {

    //태그 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    //태그 이름
    @Column(name = "tag_name", length=20)
    private String tagName;

    //사용자 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    //카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    //공통 데이터
    @Embedded
    private CommonData commonData;

    public void registTag (Long tagId, String tagName, User user, Category category, CommonData commonData) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.user = user;
        this.category = category;
        this.commonData = commonData;
    }

    private Tag(String tagName, User user, Category category) {
        this.tagName = tagName;
        this.user = user;
        this.category = category;
    }

    public static Tag createTag(String tagName, User user, Category category) {
        return new Tag(tagName, user, category);
    }
}