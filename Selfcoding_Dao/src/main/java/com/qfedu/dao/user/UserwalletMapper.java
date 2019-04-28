package com.qfedu.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.entity.user.Userwallet;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 冯志立
 * @since 2019-03-20
 */
public interface UserwalletMapper extends BaseMapper<Userwallet> {

    int updateByCions(@Param("cions") int cions, @Param("id") int id);
}