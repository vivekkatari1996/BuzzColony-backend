package com.idea.buzzcolony.service;

import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.dto.vimeo.VimeoRespDto;

import java.io.IOException;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 07/02/21
 */
public interface VimeoService {

    VimeoRespDto resumableUpload(FileDto fileDto) throws IOException;

    void delete(String link) throws Exception;

    String getTransCodeStatus(String link) throws IOException;

}
