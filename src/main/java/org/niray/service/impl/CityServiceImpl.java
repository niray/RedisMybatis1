package org.niray.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import org.niray.entity.City;
import org.niray.mapper.CityMapper;
import org.niray.service.ICityService;
import org.springframework.stereotype.Service;

/**
 *
 * City 表数据服务层接口实现类
 *
 */
@Service
public class CityServiceImpl extends SuperServiceImpl<CityMapper, City> implements ICityService {


}