package com.mapbefine.mapbefine.permission.domain;

import static lombok.AccessLevel.PROTECTED;

import com.mapbefine.mapbefine.common.entity.BaseTimeEntity;
import com.mapbefine.mapbefine.member.domain.Member;
import com.mapbefine.mapbefine.topic.domain.Topic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Permission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private Permission(Topic topic, Member member) {
        this.topic = topic;
        this.member = member;
    }

    public static Permission createPermissionAssociatedWithTopicAndMember(
            Topic topic,
            Member member
    ) {
        Permission permission = new Permission(topic, member);
        topic.addMemberTopicPermission(permission);
        member.addPermission(permission);

        return permission;
    }

}
