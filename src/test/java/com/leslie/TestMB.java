package com.leslie;

import com.leslie.mapper.CityMapper;
import com.leslie.mapper.ProvinceMapper;
import com.leslie.pojo.City;
import com.leslie.pojo.Province;
import com.leslie.service.LocationService;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMB {

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Test
    public void test1(){
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = factoryBuilder.build(new Configuration());
        System.out.println(factory);
        SqlSession sqlSession = factory.openSession();
        CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
        City city = cityMapper.selectById(1L);
        System.out.println(city);
    }

    @Test
    public void test2(){
        System.out.println(locationService);
        Province province = provinceMapper.selectById(1L);
        System.out.println(province);
    }


}
