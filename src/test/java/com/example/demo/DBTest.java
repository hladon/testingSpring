package com.example.demo;

import com.example.demo.DAO.JpaConfig;
import com.example.demo.DAO.MemberRepository;
import com.example.demo.DAO.ProductRepository;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {JpaConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class DBTest {
    @Autowired
    private MemberRepository memberRepository;
    @Resource
    private Service service;
    @Resource
    private ProductRepository productRepository;

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
    public void whenCreateMemberSave() throws Exception{

        String date="03/03/2020";
        String fullName="John Week";
        String phone="+3(095)345-34-34";
        String address="some city";
        String email="wetre@gdfgs.com";
        String gender="MALE";
        String userName="John";
        String setPassword="123";
        Member member=service.createMember(userName,setPassword,email,fullName,address,phone,date,gender);
        Assertions.assertThrows(ConstraintViolationException.class,()->{

        });
    }
    @Test
    public void whenCreateNameNotValidNameThrowException() {

        String date="03/03/2020";
        String fullName="John Week";
        String phone="+3(095)345-34-34";
        String address="some city";
        String email="wetre@gdfgs.com";
        String gender="MALE";
        String userName="John132";
        String setPassword="123";
        Assertions.assertThrows(ConstraintViolationException.class,()->{
            service.createMember(userName,setPassword,email,fullName,address,phone,date,gender);
        });
    }
    @Test
    public void whenAddCommentAllOk() throws Exception{

        String date="03/03/2020";
        String fullName="John Week";
        String phone="+3(095)345-34-34";
        String address="some city";
        String email="wetre@gdfgs.com";
        String gender="MALE";
        String userName="John132";
        String setPassword="123";
        Member member=service.createMember(userName,setPassword,email,fullName,address,phone,date,gender);
        Product product=new Product();
        product.setDescription("some goods");
        product.setProductName("goods");
        productRepository.save(product);
        Assertions.assertTrue(service.addComment(product,member,"some text"));
    }
    @Test
    public void whenAddCommentProductNotExistThrowsException() throws Exception{

        String date="03/03/2020";
        String fullName="John Week";
        String phone="+3(095)345-34-34";
        String address="some city";
        String email="wetre@gdfgs.com";
        String gender="MALE";
        String userName="John132";
        String setPassword="123";
        Member member=service.createMember(userName,setPassword,email,fullName,address,phone,date,gender);
        Product product=new Product();
        product.setDescription("some goods");
        product.setProductName("goods");
        Assertions.assertThrows(Exception.class,()->{
            service.addComment(product,member,"some text");
        });
    }
}
