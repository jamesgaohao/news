package com.caicai.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caicai.dao.NewsService;
import com.caicai.entity.news.News;
import com.caicai.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
  @Autowired
   private NewsMapper newsMapper;
    @Override
    public IPage<News> getpage(Page<News> page, LambdaQueryWrapper<News> queryWrapper) {

        return  newsMapper.selectPage(page,queryWrapper);
    }
}
