package com.caicai.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caicai.entity.news.News;

public interface NewsService extends IService<News> {


    IPage<News> getpage(Page<News> page, LambdaQueryWrapper<News> queryWrapper);
}
