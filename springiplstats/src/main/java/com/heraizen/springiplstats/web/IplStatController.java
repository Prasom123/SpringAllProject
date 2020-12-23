package com.heraizen.springiplstats.web;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heraizen.springiplstats.config.AppEmailService;
import com.heraizen.springiplstats.domain.Address;
import com.heraizen.springiplstats.domain.UserProfile;
import com.heraizen.springiplstats.dto.PlayerDTO;
import com.heraizen.springiplstats.dto.RoleAmountDTO;
import com.heraizen.springiplstats.dto.RoleCountDTO;
import com.heraizen.springiplstats.dto.TeamAmountDTO;
import com.heraizen.springiplstats.dto.TeamDTO;
import com.heraizen.springiplstats.dto.TeamLabelsDTO;
import com.heraizen.springiplstats.dto.UserProfileDTO;
import com.heraizen.springiplstats.exception.DataNotFoundException;

import com.heraizen.springiplstats.exception.TeamLabelNotFoundException;
import com.heraizen.springiplstats.service.IplStatService;

@RestController
@RequestMapping("/api/v1/stat/")
public class IplStatController {

	@Autowired
	private IplStatService iplStatService;
	@Autowired
	private AppEmailService appEmailService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("labels")
	public TeamLabelsDTO getLabels() throws DataNotFoundException {
		 appEmailService.sendSimpleMessage("badalraj2198@gmail.com", "Congratulation",
		 "Wish you happy new year ");
		return iplStatService.getTeamLabels();
	}

	@GetMapping("players/{label}")
	public List<PlayerDTO> getPlayers(@PathVariable("label") String label) throws TeamLabelNotFoundException {
		return iplStatService.getPlayerByLabel(label);
	}

	@GetMapping("allPlayers")
	public List<PlayerDTO> getPlayers() throws TeamLabelNotFoundException, DataNotFoundException {
		return iplStatService.getAllPlayers();
	}

	@GetMapping("roleAmount/{label}")
	public List<RoleAmountDTO> getAmountSpentedByLabelEachRole(@PathVariable("label") String label)
			throws TeamLabelNotFoundException, DataNotFoundException {
		return iplStatService.getAmountSpentOnRoleByLabel(label);
	}

	@GetMapping("roleCount/{label}")
	public List<RoleCountDTO> getRoleCount(@PathVariable("label") String label)
			throws TeamLabelNotFoundException, DataNotFoundException {
		return iplStatService.getRoleCountByLabel(label);
	}

	@GetMapping("spentedAmount/{label}")
	public TeamAmountDTO getAmountSpentedByTeam(@PathVariable("label") String label)
			throws TeamLabelNotFoundException, DataNotFoundException {
		return iplStatService.getAmountSpentByTeam(label);
	}

	@GetMapping("teamDetails")
	public List<TeamDTO> getAmountSpentedByTeam() throws DataNotFoundException {
		return iplStatService.getTeamDetails();
	}

	@GetMapping("profileDetails")
	public UserProfileDTO getProfileDetails()  {

		Address address = Address.builder().city("Begusarai").state("Bihar").country("India").build();
		UserProfile profile = UserProfile.builder().username("Badal123").password("Badal12@#")
				.email("badalraj2198@gmail.com").address(address).build();
		
		UserProfileDTO userProfileDTO = modelMapper.map(profile, UserProfileDTO.class);
		
//		TypeMap<UserProfile, UserProfileDTO> typeMap = modelMapper.typeMap(UserProfile.class, UserProfileDTO.class).addMappings(mapper -> {
//			 
//			 mapper.map(src-> src.getAddress().getCountry(), UserProfileDTO::setCountry);
//
//			});
//		UserProfileDTO userProfileDTO = typeMap.map(profile);

		return userProfileDTO;
	}

}
