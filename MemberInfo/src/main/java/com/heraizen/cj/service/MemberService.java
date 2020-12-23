package com.heraizen.cj.service;

import java.util.Comparator;
import java.util.List;

import com.heraizen.cj.domain.Member;

public interface MemberService {
	public Member addMemeber(Member member);

	public void deleteMember(String id);

	public List<Member> displayMembers(Comparator<Member> comparator);

	public Member searchById(String id);

	public Member searchByIdOrName(String id, String name);
}
