package com.qfedu.dao.pay;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.entity.pay.Payorder;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Feri
 * @since 2019-03-20
 */
public interface PayorderMapper extends BaseMapper<Payorder> {
    int updateFlag(@Param("id") int id, @Param("flag") int flag);
}