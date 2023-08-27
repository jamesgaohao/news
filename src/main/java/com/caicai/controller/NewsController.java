package com.caicai.controller;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caicai.common.*;
import com.caicai.dao.NewsService;
import com.caicai.dao.SubScriptTypeService;
import com.caicai.dao.UserReadNewsRecordService;
import com.caicai.entity.news.News;
import com.caicai.entity.subcribetype.SubscribeType;
import com.caicai.entity.user.User;
import com.caicai.entity.userReadNewsRecord.UserReadNewsRecord;
import com.caicai.model.NewsModel;
import com.caicai.util.GetCurrentUserUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.*;
import java.util.List;

@RestController
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserReadNewsRecordService userReadNewsRecordService;

    @Autowired
    private SubScriptTypeService subScriptTypeService;

    @Autowired
    private GetCurrentUserUtil getCurrentUserUtil;

    @GetMapping("/getNews")
    public ActionResult getSourceNews(Pagination pagination) {
        Page<News> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(News::getPushTime);
        IPage<News> pageRes = newsService.getpage(page, queryWrapper);
        PaginationVO page1 = new PaginationVO();
        page1.setCurrentPage(pagination.getCurrentPage());
        page1.setPageSize(pagination.getPageSize());
        long total = pageRes.getTotal();
        page1.setTotal((int) total);
        PageListVO vo = new PageListVO();
        vo.setList(pageRes.getRecords());
        vo.setPagination(page1);
        return ActionResult.success(vo);
    }

    /**
     * 新闻推荐接口
     *
     * @return
     */
    @PostMapping("/getRetrieve")
    public ActionResult getRetrieve(@RequestBody NewsModel newsModel) {
        User user = null;
        PaginationVO page1 = new PaginationVO();
        PageListVO vo = new PageListVO();
        IPage<News> pageRes = null;
        Page<News> pageRetrieve = new Page<>(newsModel.getCurrentPage(), newsModel.getPageSize());
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        // 获取当前登录用户信息
        user  = getCurrentUserUtil.getCurrentUserInfo(newsModel.getUserName());
        if(user!=null){
            // 获取用户阅读记录
            List<String> types = userReadNewsRecordService.getUserReadType(user.getId());
            if(types!=null&&types.size()>0){
                queryWrapper.in(News::getSubscribeId, types);
            }
        }
        queryWrapper.orderByDesc(News::getPushTime);
        pageRes =  newsService.getpage(pageRetrieve, queryWrapper);
        page1.setCurrentPage(newsModel.getCurrentPage());
        page1.setPageSize(newsModel.getPageSize());
        page1.setTotal(0);
        vo.setPagination(page1);
        if(pageRes!=null){
            long total = pageRes.getTotal();
            page1.setTotal((int) total);
            vo.setList(pageRes.getRecords());
        }else {
            vo.setList(new ArrayList());
        }


        return ActionResult.success(vo);

    }


    /**
     * 获取当前用户新闻订阅接口
     *
     * @return
     */
    @GetMapping("/getUserSubNews")
    public ActionResult getUserSub( NewsModel newsModel) {
        User user = null;
        // 获取当前登录用户信息
        user  = getCurrentUserUtil.getCurrentUserInfo(newsModel.getUserName());
        if(user!=null){
            IPage<News> pageRes = null;
            Page<News> pageRetrieve = new Page<>(newsModel.getCurrentPage(), newsModel.getPageSize());
            PaginationVO page1 = new PaginationVO();
            PageListVO vo = new PageListVO();
            String subscribe = user.getSubscribe();
            if(StringUtils.isBlank(subscribe)){
                return ActionResult.fail("您还没有订阅新闻");
            }
            final String[] subscribeIds = subscribe.split(",");
            LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(News::getSubscribeId,subscribeIds);
            queryWrapper.orderByDesc(News::getPushTime);
            pageRes =  newsService.getpage(pageRetrieve, queryWrapper);
            page1.setCurrentPage(newsModel.getCurrentPage());
            page1.setPageSize(newsModel.getPageSize());
            page1.setTotal(0);
            vo.setPagination(page1);
            if(pageRes!=null){
                long total = pageRes.getTotal();
                page1.setTotal((int) total);
                vo.setList(pageRes.getRecords());
            }else {
                vo.setList(new ArrayList());
            }
            return ActionResult.success(vo);
        }
        return ActionResult.fail("当前用户没有登录");



    }


    /**
     * 获取新闻详情
     *
     * @return
     */
    @GetMapping("/getNewsInfo/{id}")
    public ActionResult getNewsInfo(@PathVariable("id") String id, NewsModel newsModel) {
        User user = null;
        // 获取当前登录用户信息
        // 获取当前登录用户信息
        user  = getCurrentUserUtil.getCurrentUserInfo(newsModel.getUserName());
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(News::getId, id);
        queryWrapper.orderByDesc(News::getPushTime);
        News newsServiceOne = newsService.getOne(queryWrapper);
        if (newsServiceOne != null) {
            // 创建用户阅读记录
            UserReadNewsRecord userReadNewsRecord = new UserReadNewsRecord();
            userReadNewsRecord.setUserId(user==null?"0":user.getId());
            userReadNewsRecord.setTitle1(newsServiceOne.getTitle1());
            userReadNewsRecord.setSubscribe(newsServiceOne.getSubscribeId());
            userReadNewsRecord.setCreateTime(new Date());
            userReadNewsRecordService.save(userReadNewsRecord);
        }
        return ActionResult.success(newsServiceOne);

    }





    /**
     * 新闻
     *
     * @return
     */

    @GetMapping("/getSourceNews")
    public ActionResult getSourceNews() {
        try {


            //财经、
            Map<String, String> Finance = new HashMap<>();
            Finance.put("Finance", "#Con71 > table > tbody > tr");
            //社会、
            Map<String, String> Society = new HashMap<>();
            Society.put("Society", "#Con41 > table > tbody > tr");

            //娱乐、 #Con81 > table > tbody > tr:nth-child(4) > td.ConsTi > a
            Map<String, String> Entertainment = new HashMap<>();
            Entertainment.put("Entertainment", "#Con81 > table > tbody > tr");

            //科技、#Con62 > table > tbody > tr:nth-child(4) > td.ConsTi > a
            Map<String, String> Technology = new HashMap<>();
            Technology.put("Technology", "#Con61 > table > tbody > tr");
            //国际 #Con31 > table > tbody > tr:nth-child(4) > td.ConsTi > a
            Map<String, String> International = new HashMap<>();
            International.put("International", "#Con31 > table > tbody > tr");
            //、国内#Con21 > table > tbody > tr:nth-child(4) > td.ConsTi > a
            Map<String, String> Domestic = new HashMap<>();
            Domestic.put("Domestic", "#Con21 > table > tbody > tr");
            //、军事 #Con91 > table > tbody > tr:nth-child(4) > td.ConsTi > a
            Map<String, String> Military = new HashMap<>();
            Military.put("Military", "#Con91 > table > tbody > tr");

            List<Map<String, String>> para = new ArrayList<>();
            para.add(Finance);
            para.add(Society);
            para.add(Entertainment);
            para.add(International);
            para.add(Technology);
            para.add(Domestic);
            para.add(Military);
            // 设置key
            List<String> key = new ArrayList<>();
            key.add("Finance");
            key.add("Society");
            key.add("Entertainment");
            key.add("International");
            key.add("Technology");
            key.add("Domestic");
            key.add("Military");
             // 使用Jsoup连接目标网站
             Document doc = getDocument("https://news.sina.com.cn/hotnews/");
            for (int j = 4; j < 7; j++) {
                // 获取新闻列表'
                Elements newsList = doc.select(para.get(j).get(key.get(j)));
                final String type = key.get(j);
                LambdaQueryWrapper<SubscribeType> querySubWrapper = new LambdaQueryWrapper<>();
                querySubWrapper.like(SubscribeType::getSubscribeName,type);
                querySubWrapper.last("limit 1");
                final SubscribeType subOne = subScriptTypeService.getOne(querySubWrapper);
                List<Map<String, Object>> list = new ArrayList<>();
                // 爬取前热点新闻
                for (int i = 1; i < newsList.size(); i++) {
                    Map<String, Object> res = new HashMap<>();
                    Element news = newsList.get(i);
                    // 获取新闻标题
                    String title = news.select("td").get(1).text();
                    res.put("title", title);

                    // 获取记者姓名
                    String reporter = news.select("td").get(2).text();
                    res.put("reporter", reporter);
                    String link = news.select("a").first().attr("href");
                    // 获取新闻内容
                    Document docContent = getDocument(link);
                    Elements contentEl = new Elements();
                    contentEl = docContent.select("#article > p");
                     if(contentEl.size()<=0){
                         contentEl = docContent.select("#artibody");
                     }
                    Elements contentTimeEl = docContent.select("#top_bar > div > div.date-source > span");
                    String contentTime = contentTimeEl.first().text();
                    String content = contentEl.text();
                    res.put("content", content);
                    // 获取发布时间
                    list.add(res);
                    // 打印爬取结果
                    System.out.println("新闻标题：" + title);
                    System.out.println("新闻内容：" + content);
                    System.out.println("链接地址：" + link);
                    System.out.println("发布者：" + reporter);
                    System.out.println("发布时间：" + contentTime);
                    System.out.println("----------------------------------");
                    RandomUtil.uuId();

                    News newsEntity = new News();
                    newsEntity.setId(RandomUtil.uuId());
                    newsEntity.setTitle1(title);
                    newsEntity.setContent(content);
                    newsEntity.setPublisher(reporter);
                    newsEntity.setPushTime(DateUtil.parse(contentTime));
                    newsEntity.setCreateTime(DateUtil.parse(DateUtil.now()));
                    if(subOne!=null){
                        newsEntity.setSubscribeId(subOne.getId());
                        newsEntity.setTitle2(subOne.getUrl());
                    }
                    LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(News::getTitle1, title);
                    queryWrapper.last("limit 1 ");
                    News newsServiceOne = newsService.getOne(queryWrapper);
                    log.info("获取是否由同一标题新闻 查询结果: {}", newsServiceOne);
                    if (newsServiceOne == null) {
                        // 如果新闻不存在，则保存
                        newsService.save(newsEntity);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.success();
    }

    private Document getDocument(String url) {

        Document document = null;
        try {
            //动态抓取
            WebClient webClient = new WebClient();
            webClient.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());
            webClient.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
            webClient.getOptions().setCssEnabled(false); //禁用css支持
            webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
            HtmlPage page = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(3 * 1000);
            String pageXml = page.asXml(); //以xml的形式获取响应文本
            document = Jsoup.parse(pageXml);

        } catch (IOException e) {
        }

        return document;

    }


}
