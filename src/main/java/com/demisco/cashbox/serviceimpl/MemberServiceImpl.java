package com.demisco.cashbox.serviceimpl;

import com.demisco.cashbox.exception.RuleException;
import com.demisco.cashbox.model.Member;
import com.demisco.cashbox.repository.MemberRepository;
import com.demisco.cashbox.request.MemberRequest;
import com.demisco.cashbox.response.MemberResponse;
import com.demisco.cashbox.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Long save(MemberRequest request) {
        if (request.getId() == null)
            validationBeforeMap(request);

        Member member = mapToEntity(request);
        member = memberRepository.save(member);
        return member.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponse> getAll() {
        return memberRepository.findAll().stream().map(
                        member -> MemberResponse.builder()
                                .id(member.getId())
                                .firstName(member.getFirstName())
                                .lastName(member.getLastName())
                                .mobile(member.getMobile())
                                .nationalCode(member.getNationalCode())
                                .build())
                .collect(Collectors.toList());

    }

    @Override
    public Boolean delete(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty())
            throw new RuleException("کاربر یافت نشد");
        memberRepository.delete(member.get());
        return true;
    }

    @Override
    public MemberResponse findById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty())
            throw new RuleException("کاربر یافت نشد");
        return member.stream().map(
                m -> MemberResponse.builder()
                        .id(m.getId())
                        .firstName(m.getFirstName())
                        .lastName(m.getLastName())
                        .mobile(m.getMobile())
                        .nationalCode(m.getNationalCode())
                        .build()).findFirst().orElse(null);
    }

    private void validationBeforeMap(MemberRequest request) {
        Optional<Member> member = memberRepository.findByNationalCode(request.getNationalCode());
        if (member.isPresent())
            throw new RuleException("کاربر قبلا ثبت نام کرده است");
//            throw new RuleException(MessageBundleImpl.message("member-o1"));
    }

    private Member mapToEntity(MemberRequest request) {
        return Member.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .mobile(request.getMobile())
                .nationalCode(request.getNationalCode())
                .build();
    }
}
