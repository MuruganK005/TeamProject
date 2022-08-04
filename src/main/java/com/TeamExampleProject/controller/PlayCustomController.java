package com.TeamExampleProject.controller;

import com.TeamExampleProject.Export.ExcelExport;
import com.TeamExampleProject.Export.PdfExport;
import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.page.PageDao;
import com.TeamExampleProject.dao.searchCriteria.Document;
import com.TeamExampleProject.repo.PlayRepo;
import com.TeamExampleProject.service.page.PageService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.TeamExampleProject.Specification.PlaySpecification.hasPlayName;

@RestController
@RequestMapping("/api/v1")
public class PlayCustomController {
    @Autowired
    private PlayRepo customRepo;
    @Autowired
    private PageService pageService;

    public PlayCustomController (PageService pageService){
        this.pageService=pageService;
    }

    @GetMapping("/playName")
    public List<Play> findByPlayName(@PathVariable("playName") String playName){
        return customRepo.findAll((hasPlayName(playName)));
    }
    @GetMapping("/play/{numberOfPages}/{maxSize}")
    public Page<Play> getFilterPlay(@PathVariable Play numberOfPages, @PathVariable Pageable maxSize){
        return customRepo.getFilterPlay(numberOfPages,maxSize);
    }
    @GetMapping("/playName/pageDao")
    public Page<Play> getPlays(@RequestBody PageDao pageDao){
        return pageService.getPlays(pageDao);
    }
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=Play.xlsx";

        response.setHeader(headerKey, headervalue);
        List<Play> playList = customRepo.findAll();
        ExcelExport exp = new ExcelExport(playList);
        exp.export(response);
    }
    @GetMapping("/play/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Play> listPlays= customRepo.findAll();

        PdfExport exporter = new PdfExport(listPlays);
        exporter.export(response);
    }
    @PostMapping("/upload")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get("fileBasePath" + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }
    @PostMapping("/multi-upload")
    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(files)
                .stream()
                .forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
        return ResponseEntity.ok(fileDownloadUrls);
    }


}
