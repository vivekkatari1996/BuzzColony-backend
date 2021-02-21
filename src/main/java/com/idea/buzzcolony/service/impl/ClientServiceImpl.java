package com.idea.buzzcolony.service.impl;

import com.idea.buzzcolony.dto.client.PostAddressDto;
import com.idea.buzzcolony.dto.client.PostDto;
import com.idea.buzzcolony.dto.master.*;
import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.dto.vimeo.VimeoRespDto;
import com.idea.buzzcolony.enums.base.FileType;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.base.BaseEntity;
import com.idea.buzzcolony.model.base.FileEntity;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.client.PostAddress;
import com.idea.buzzcolony.model.master.*;
import com.idea.buzzcolony.repo.AppUserRepo;
import com.idea.buzzcolony.repo.FileEntityRepo;
import com.idea.buzzcolony.repo.client.PostRepo;
import com.idea.buzzcolony.repo.master.*;
import com.idea.buzzcolony.service.ClientService;
import com.idea.buzzcolony.service.VimeoService;
import com.idea.buzzcolony.util.ApiResponse;
import com.idea.buzzcolony.util.AppMessage;
import com.idea.buzzcolony.util.Constants;
import com.idea.buzzcolony.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private VimeoService vimeoService;

    @Autowired
    private FileEntityRepo fileEntityRepo;

    @Autowired
    private MtEstPartRepo mtEstPartRepo;

    @Autowired
    private MtEstAmountRepo mtEstAmountRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse saveOrUpdatePost(PostDto postDto) throws Exception {
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Long id = createPost(postDto, appUser);
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), id);
    }

    private Long createPost(PostDto postDto, AppUser appUser) throws Exception {
        Post post = postRepo.findById(postDto.getId()).orElse(new Post());
        MtSubBtype mtSubBtype = mtSubBtypeRepo.findById(postDto.getMtSubBTypeId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        MtEstAmount mtEstAmount = mtEstAmountRepo.findById(postDto.getMtEstAmountId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        MtEstPart mtEstPart = mtEstPartRepo.findById(postDto.getMtEstPartId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        MtCategory mtCategory = mtCategoryRepo.findById(postDto.getMtCategoryId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        post.setAppUser(appUser);
        post.setMtSubBtype(mtSubBtype);
        post.setBType(postDto.getBType());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setIsPhNoHidden(postDto.getIsPhNoHidden());
        post.setOccupation(postDto.getOccupation());
        post.setPostAddress(convertPostAddressDtoToEntity(postDto.getPostAddressDto(), post));
        post.setMtCategory(mtCategory);
        post.setMtEstAmount(mtEstAmount);
        post.setMtEstPart(mtEstPart);
        post = postRepo.save(post);
        return post.getId();
    }

    private PostAddress convertPostAddressDtoToEntity(PostAddressDto postAddressDto, Post post) throws Exception {
        PostAddress postAddress;
        if (post.getPostAddress() == null) {
            postAddress = new PostAddress();
        } else {
            postAddress = post.getPostAddress();
        }
        MtCountry mtCountry = mtCountryRepo.findById(postAddressDto.getMtCountryId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        postAddress.setCity(postAddress.getCity());
        postAddress.setDistrict(postAddress.getDistrict());
        postAddress.setState(postAddress.getState());
        postAddress.setTown(postAddress.getTown());
        postAddress.setMtCountry(mtCountry);
        return postAddress;
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

    @Override
    public ApiResponse getEstPartners() {
        List<MtEstPart> mtEstParts = mtEstPartRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtEstParts.stream().map(MtEstPartDto::new).collect(Collectors.toList()));
    }

    @Override
    public ApiResponse getEstAmounts() {
        List<MtEstAmount> mtEstAmounts = mtEstAmountRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtEstAmounts.stream().map(MtEstAmountDto::new).collect(Collectors.toList()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse uploadVidep(FileDto fileDto) throws Exception {
        ApiResponse apiResponse = ApiResponse.getFailureResponse();
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Post post = postRepo.findByIdAndAppUser(fileDto.getRefId(), appUser).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        Optional<FileEntity> optionalFileEntity = fileEntityRepo.findByRefIdAndFileType(post.getId(), FileType.POST);
        if (optionalFileEntity.isPresent()) {
            vimeoService.delete(optionalFileEntity.get().getUuid());
            fileEntityRepo.deleteByIdManual(optionalFileEntity.get().getUuid());
        }
        VimeoRespDto vimeoRespDto = vimeoService.resumableUpload(fileDto);
        FileEntity fileEntity = new FileEntity();
        fileEntity.setUuid(vimeoRespDto.getEndPoint());
        fileEntity.setFileType(FileType.POST);
        fileEntity.setName(fileDto.getName());
        fileEntity.setRefId(post.getId());
        fileEntity.setSize(fileDto.getSize());
        fileEntity.setType(fileDto.getType());
        fileEntityRepo.save(fileEntity);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setMessage(appMessage.getMessage("success"));
        apiResponse.setData(vimeoRespDto);
        return apiResponse;
    }

    @Override
    public ApiResponse getTransCodeStatus(Long id) throws Exception{
        ApiResponse apiResponse = ApiResponse.getFailureResponse();
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Post post = postRepo.findByIdAndAppUser(id, appUser).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        Optional<FileEntity> optionalFileEntity = fileEntityRepo.findByRefIdAndFileType(post.getId(), FileType.POST);
        if (optionalFileEntity.isPresent()) {
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setMessage(appMessage.getMessage("success"));
            apiResponse.setData(vimeoService.getTransCodeStatus(optionalFileEntity.get().getUuid()));
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getPosts(PostDto postDto) throws Exception {
        ApiResponse apiResponse = ApiResponse.getFailureResponse();
        Page<Post> posts = getPostsUsingCriteriaBuilder(postDto);
        List<FileEntity> videos = fileEntityRepo.findByRefIdInAndFileType(posts.getContent().stream().map(BaseEntity::getId).collect(Collectors.toList()), FileType.POST);
        if (posts.getSize() != videos.size()) {
            throw new Exception(appMessage.getMessage("data.not.found"));
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setMessage(appMessage.getMessage("success"));
        List<PostDto> postDtos = posts.getContent().stream().map(post -> new PostDto(post, videos.stream().filter(i -> i.getRefId().longValue() == post.getId().longValue()).findFirst().get().getUuid())).collect(Collectors.toList());
        apiResponse.setData(new PageImpl<>(postDtos, posts.getPageable(), posts.getTotalElements()));
        return apiResponse;
    }

    public Page<Post> getPostsUsingCriteriaBuilder(PostDto postDto) {
        int page = postDto.getPage();
        String search = postDto.getSearch();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = cb.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        Path<Object> appUser = root.get("appUser");
        root.alias("rootAlias");
        List<Predicate> predicateList = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            search = search.replaceAll("\\s", "");
            Expression<String> function = cb.function("replace",
                    String.class, cb.concat(cb.upper(appUser.get("firstName")), cb.upper(appUser.get("lastName"))), cb.literal(" "),
                    cb.literal(""));
            Predicate searchQueryPredicate = cb.or(cb.like(function, "%" + search.toUpperCase() + "%"), cb.like(cb.upper(root.get("title")), "%" + search.toUpperCase() + "%"));
            predicateList.add(searchQueryPredicate);
        }

        if (postDto.getMtCategoryId() != null && postDto.getMtCategoryId() != 0L) {
            Predicate mtCategoryId = cb.or(cb.equal(root.get("mtCategory").get("id"), postDto.getMtCategoryId()));
            predicateList.add(mtCategoryId);
        }

        if (postDto.getMtEstAmountId() != null && postDto.getMtEstAmountId() != 0L) {
            Predicate mtEstAmount = cb.or(cb.equal(root.get("mtEstAmount").get("id"), postDto.getMtEstAmountId()));
            predicateList.add(mtEstAmount);
        }

        if (postDto.getMtEstPartId() != null && postDto.getMtEstPartId() != 0L) {
            Predicate mtEstPart = cb.or(cb.equal(root.get("mtEstPart").get("id"), postDto.getMtEstPartId()));
            predicateList.add(mtEstPart);
        }

        if (postDto.getMtSubBTypeId() != null && postDto.getMtSubBTypeId() != 0L) {
            Predicate mtSubBtype = cb.or(cb.equal(root.get("mtSubBtype").get("id"), postDto.getMtSubBTypeId()));
            predicateList.add(mtSubBtype);
        }

        if (postDto.getMtCountryId() != null && postDto.getMtCountryId() != 0L) {
            Predicate mtEstPart = cb.or(cb.equal(root.get("postAddress").get("mtCountry").get("id"), postDto.getMtCountryId()));
            predicateList.add(mtEstPart);
        }

        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        if (search != null && !search.isEmpty()) {
            criteriaQuery.orderBy(Arrays.asList(cb.asc(root.get("appUser").get("firstName")), cb.asc(root.get("appUser").get("lastName"))));
        } else {
            criteriaQuery.orderBy(cb.desc(root.get("createdOn")));
        }
        CriteriaQuery<Long> criteriaQueryForCount = cb.createQuery(Long.class);
        Root<Post> root1 = criteriaQueryForCount.from(criteriaQuery.getResultType());
        root1.alias("rootAlias");
        criteriaQueryForCount.select(cb.count(root1));
        criteriaQueryForCount.where(predicateList.toArray(new Predicate[0]));

        Long total = entityManager.createQuery(criteriaQueryForCount).getSingleResult();

        TypedQuery<Post> typedQuery = entityManager.createQuery(criteriaQuery);

        if (page == 0) typedQuery.setFirstResult(page);
        else typedQuery.setFirstResult(page * Constants.PAGE_SIZE);
        typedQuery.setMaxResults(Constants.PAGE_SIZE);

        List<Post> posts = typedQuery.getResultList();
        return new PageImpl<>(posts, PageRequest.of(page, Constants.PAGE_SIZE), total);
    }

}
