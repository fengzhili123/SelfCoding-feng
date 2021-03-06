package com.qfedu.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.entity.user.Usersign;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Feri
 * @since 2019-03-20
 */

public interface UsersignMapper extends BaseMapper<Usersign> {

    Usersign queryLastSign(int uid);
}