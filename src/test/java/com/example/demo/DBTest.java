package com.example.demo;

import com.example.demo.DAO.JpaConfig;
import com.example.demo.DAO.MemberRepository;
import com.example.demo.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {JpaConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class DBTest {
    @Resource
    private MemberRepository memberRepository;

    @Test
    public void givenMember_whenSave_thenGetOk() {
        Member member = new Member();
        member.setMemberId(1);
        member.setFullName("test");
        memberRepository.save(member);

        Member member2 = memberRepository.findById(1).get();
        assertEquals("test", member2.getUserName());
    }
    @Test
    public void whenNotValidNameThrowException(){
        Member member=new Member(0,);
        Assertions.assertThrows(ConstraintViolationException.class,()->{

        })
    }
}
