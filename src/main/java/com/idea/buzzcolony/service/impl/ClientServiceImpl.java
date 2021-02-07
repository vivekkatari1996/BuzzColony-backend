package com.idea.buzzcolony.service.impl;

import com.idea.buzzcolony.dto.client.PostAddressDto;
import com.idea.buzzcolony.dto.client.PostDto;
import com.idea.buzzcolony.dto.master.MtBtypeDto;
import com.idea.buzzcolony.dto.master.MtCategoryDto;
import com.idea.buzzcolony.dto.master.MtCountryDto;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.client.PostAddress;
import com.idea.buzzcolony.model.master.MtBtype;
import com.idea.buzzcolony.model.master.MtCategory;
import com.idea.buzzcolony.model.master.MtCountry;
import com.idea.buzzcolony.model.master.MtSubBtype;
import com.idea.buzzcolony.repo.AppUserRepo;
import com.idea.buzzcolony.repo.client.PostRepo;
import com.idea.buzzcolony.repo.master.MtBTypeRepo;
import com.idea.buzzcolony.repo.master.MtCategoryRepo;
import com.idea.buzzcolony.repo.master.MtCountryRepo;
import com.idea.buzzcolony.repo.master.MtSubBtypeRepo;
import com.idea.buzzcolony.service.ClientService;
import com.idea.buzzcolony.util.ApiResponse;
import com.idea.buzzcolony.util.AppMessage;
import com.idea.buzzcolony.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AppMessage appMessage;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private MtCategoryRepo mtCategoryRepo;

    @Autowired
    private MtCountryRepo mtCountryRepo;

    @Autowired
    private MtSubBtypeRepo mtSubBtypeRepo;

    @Autowired
    private MtBTypeRepo mtBTypeRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse saveOrUpdatePost(PostDto postDto) throws Exception {
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        createPost(postDto, appUser);
        return ApiResponse.getSuccessResponse();
    }

    private void createPost(PostDto postDto, AppUser appUser) throws Exception {
        Optional<Post> optionalPost = postRepo.findById(postDto.getId());
        if (!optionalPost.isPresent() || optionalPost.get().getAppUser().getId().longValue() != appUser.getId().longValue()) {
            throw new Exception(appMessage.getMessage("data.not.found"));
        }
        MtSubBtype mtSubBtype = mtSubBtypeRepo.findById(postDto.getMtSubBTypeId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        Post post;
        if (optionalPost.isPresent()) {
            post = optionalPost.get();
        } else {
            post = new Post();
        }
        post.setAppUser(appUser);
        post.setMtSubBtype(mtSubBtype);
        post.setBType(postDto.getBType());
        post.setTitle(postDto.getTitle());
        post.setEstimatedPrs(postDto.getEstimatedPartners());
        post.setDescription(postDto.getDescription());
        post.setIsPhNoHidden(postDto.getIsPhNoHidden());
        post.setOccupation(postDto.getOccupation());
        post.setPostAddress(convertPostAddressDtoToEntity(postDto.getPostAddressDto()));
        post.setMtCategory(getMtCategory(postDto.getMtCategoryDto()));
        postRepo.save(post);
    }

    private MtCategory getMtCategory(MtCategoryDto mtCategoryDto) throws Exception {
        if (mtCategoryDto.getId() == null) {
            throw new Exception(appMessage.getMessage("data.not.found"));
        }
        return mtCategoryRepo.findById(mtCategoryDto.getId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
    }

    private PostAddress convertPostAddressDtoToEntity(PostAddressDto postAddressDto) throws Exception {
        PostAddress postAddress = new PostAddress();
        postAddress.setCity(postAddress.getCity());
        postAddress.setDistrict(postAddress.getDistrict());
        postAddress.setState(postAddress.getState());
        postAddress.setTown(postAddress.getTown());
        postAddress.setMtCountry(getMtCountry(postAddressDto.getMtCountryDto()));
        return postAddress;
    }

    private MtCountry getMtCountry(MtCountryDto mtCountryDto) throws Exception {
        if (mtCountryDto.getId() == null) {
            throw new Exception(appMessage.getMessage("data.not.found"));
        }
        return mtCountryRepo.findById(mtCountryDto.getId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
    }

    @Override
    public ApiResponse getCategories() {
        List<MtCategory> mtCategoryList = mtCategoryRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtCategoryList.stream().map(MtCategoryDto::new).collect(Collectors.toList()));
    }

    @Override
    public ApiResponse getBtypes() {
        List<MtBtype> mtBtypes = mtBTypeRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtBtypes.stream().map(MtBtypeDto::new).collect(Collectors.toList()));
    }
}
