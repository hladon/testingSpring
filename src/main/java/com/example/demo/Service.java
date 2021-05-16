package com.example.demo;

import com.example.demo.DAO.CommentRepository;
import com.example.demo.DAO.MemberRepository;
import com.example.demo.DAO.ProductRepository;
import com.example.demo.DAO.RatingRepository;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;
import com.example.demo.entity.Rating;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Validated
public class Service {

    private MemberRepository memberRepository;
    private CommentRepository commentRepository;
    private RatingRepository ratingRepository;
    private ProductRepository productRepository;

    public Service(MemberRepository memberRepository, CommentRepository commentRepository, RatingRepository ratingRepository, ProductRepository productRepository) {
        this.memberRepository = memberRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
        this.productRepository = productRepository;
    }

    public Member createMember( String userName, String password, String email, String fullName, String address, String phone, String birhdate, String gender) throws Exception {
        Member member = new Member();
        member.setUserName(userName);
        member.setPassword(password);
        member.setEmail(email);
        member.setFullName(fullName);
        member.setAddress(address);
        member.setPhone(phone);
        member.setBirtDate(LocalDate.parse(birhdate));
        member.setGender(gender);
        if ((LocalDate.now().getYear() - member.getBirtDate().getYear()) < 18)
            throw new Exception("invalid date");
        Member returnMember = memberRepository.save(member);
        if (returnMember != null)
            return returnMember;
        throw new Exception("Save problem");
    }

    public boolean addComment(Product product, Member member, String message) throws Exception {
        Comment comment = new Comment();
        checkIsMemberExist(member);
        comment.setMemberId(member);
        comment.setProductId(product);
        comment.setMessage(message);
        if (message == null || message.length() > 150 || message.isEmpty())
            return false;
        if (commentRepository.save(comment) != null)
            return true;
        return false;
    }

    public boolean addRate(Product product, Member member, int rate) throws Exception {
        Rating rating = new Rating();
        checkIsMemberExist(member);
        checkIsProductExist(product);
        if (rate > 5 || rate < 0)
            return false;
        rating.setProductId(product);
        rating.setMemberId(member);
        if (ratingRepository.save(rating) != null)
            return true;
        return false;

    }

    private void checkIsMemberExist(Member member) throws Exception {
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("memberId");
        Example<Member> example = Example.of(member, modelMatcher);
        if (!memberRepository.exists(example))
            throw new Exception("Member not exist");
    }

    private void checkIsProductExist(Product product) throws Exception {
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("productId");
        Example<Product> example = Example.of(product, modelMatcher);
        if (!productRepository.exists(example))
            throw new Exception("Product not exist");
    }
}
