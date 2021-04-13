package com.idea.buzzcolony.service.impl;

import com.clickntap.vimeo.Vimeo;
import com.clickntap.vimeo.VimeoException;
import com.clickntap.vimeo.VimeoResponse;
import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.dto.vimeo.VimeoRespDto;
import com.idea.buzzcolony.service.VimeoService;
import com.idea.buzzcolony.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 07/02/21
 */
@Service
public class VimeoServiceImpl implements VimeoService {

    @Value("${vimeo.token}")
    private String token;

    @Autowired
    private HttpServletRequest request;

    public void delete(String link) throws Exception {
        Vimeo vimeo = new Vimeo(token);
        String videoId = getVideoIdFromLink(link);
        VimeoResponse response = vimeo.delete("/videos/" + videoId);
    }

    private String getVideoIdFromLink(String link) {
        return link.substring(link.indexOf(".com/") + 5);
    }

    @Override
    public VimeoRespDto resumableUpload(FileDto fileDto) throws IOException {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name", fileDto.getName());
        params.put("privacy.download", "false");
        params.put("privacy.view", "disable");
        params.put("privacy.embed", "whitelist");
        params.put("embed.logos.vimeo", "false");
        params.put("embed.buttons.like", "false");
        params.put("embed.buttons.scaling", "false");
        params.put("embed.buttons.share", "false");
        params.put("embed.buttons.watchlater", "false");
        params.put("embed.title.name", "hide");
        params.put("embed.title.owner", "hide");
        params.put("embed.title.portrait", "hide");
        params.put("upload.approach", "tus");
        params.put("upload.size", fileDto.getSize().toString());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        headers.add("Accept", "application/vnd.vimeo.*+json;version=3.4");

        HttpEntity<String> entity = new HttpEntity<>(getParams(fileDto.getSize().toString()), headers);

        ResponseEntity<Object> response =
                restTemplate.exchange("https://api.vimeo.com/me/videos",
                        HttpMethod.POST,
                        entity,
                        Object.class);

        String uri = String.valueOf(((LinkedHashMap) response.getBody()).get("uri"));
        String uploadLink = String.valueOf(((LinkedHashMap) ((LinkedHashMap) response.getBody()).get("upload")).get("upload_link"));

        Vimeo vimeo = new Vimeo(token);
        vimeo.patch(uri, params);
        vimeo.put(uri + "/privacy/domains/" + "buzzcolony.com");
        vimeo.put(uri + "/privacy/domains/" + "localhost");
        VimeoResponse info = vimeo.getVideoInfo(uri);
        return new VimeoRespDto(String.valueOf(info.getJson().get("link")), uploadLink);
    }

    private String getParams(String size) {
        JSONObject params = new JSONObject();
        JSONObject upload = new JSONObject();
        upload.put("approach", "tus");
        upload.put("size", size);
        params.put("upload", upload);
        return params.toString();
    }

    public String getTransCodeStatus(String link) throws IOException {
        Vimeo vimeo = new Vimeo(token);
        String videoId = getVideoIdFromLink(link);
        VimeoResponse response = vimeo.get("https://api.vimeo.com/videos/" + videoId + "?fields=transcode.status");   //   uri,upload.status,transcode.status
        return String.valueOf(((JSONObject) response.getJson().get("transcode")).get("status"));
    }

}
