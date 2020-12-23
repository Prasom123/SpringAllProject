package com.heraizen.cj.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import com.heraizen.cj.domain.Member;

public class MemberServiceImpl implements MemberService {
	private static MemberServiceImpl memberServiceImpl = null;
	List<Member> memberList = new ArrayList<>();

	private MemberServiceImpl() {
		this.memberList.addAll(this.getSeedData());
	}

	public static MemberServiceImpl getInstance() {
		if (memberServiceImpl == null) {
			synchronized (MemberServiceImpl.class) {
				if (memberServiceImpl == null) {
					memberServiceImpl = new MemberServiceImpl();
				}
			}
		}
		return memberServiceImpl;
	}

	@Override
	public Member addMemeber(Member member) {
		if (member != null) {
			this.memberList.add(member);
		}
		return member;
	}

	@Override
	public void deleteMember(String id) {
		ListIterator<Member> iterator = memberList.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getMid() == id) {
				iterator.remove();
				break;
			}
		}

	}

	@Override
	public List<Member> displayMembers(Comparator<Member> comparator) {
		Collections.sort(memberList, comparator);
		return memberList;
	}

	@Override
	public Member searchById(String id) {
		Member member = null;
		for (Member memb : memberList) {
			if (memb.getMid().equalsIgnoreCase(id)) {
				member = memb;
				break;
			}
		}
		return member;
	}

	@Override
	public Member searchByIdOrName(String id, String name) {
		Member member = null;
		for (Member memb : memberList) {
			if (memb.getMid().equalsIgnoreCase(id) && memb.getName().equalsIgnoreCase(name)) {
				member = memb;
				break;
			}
		}
		return member;
	}

	public Collection<? extends Member> getSeedData() {

		Member member1 = Member.builder().name("Badal").city("Begusarai").country("India").build();
		Member member2 = Member.builder().name("Prasom").city("Patna").country("India").build();
		Member member3 = Member.builder().name("Pushkar").city("Begusarai").country("India").build();
		Member member4 = Member.builder().name("Sohit").city("Begusarai").country("India").build();
		Member member5 = Member.builder().name("Mohit").city("Bengaluru").country("India").build();
		return Arrays.asList(new Member[] { member1, member2, member3, member4, member5 });

	}

}
