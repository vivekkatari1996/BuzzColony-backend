package com.idea.buzzcolony.service;

import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.util.ApiResponse;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
public interface S3Service {

    FileDto getPreSignedUrlForUpload(FileDto fileDto) throws Exception;

    ApiResponse getPreSignedUrlForDownload(FileDto fileDto);
}
