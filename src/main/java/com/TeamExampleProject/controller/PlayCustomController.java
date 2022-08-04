package com.TeamExampleProject.controller;

import com.TeamExampleProject.Excel.ExcelExport;
import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.page.PageDao;
import com.TeamExampleProject.repo.PlayRepo;
import com.TeamExampleProject.service.page.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.io.IOException;
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
}
